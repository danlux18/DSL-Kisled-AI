package fr.kisled.dsl

import fr.kisled.dsl.builders.AppBuilder
import fr.kisled.dsl.builders.transformations.ClosureCustomizer
import fr.kisled.kernel.App
import org.codehaus.groovy.control.CompilerConfiguration

class Parser {
    CompilerConfiguration config
    GroovyShell shell
    Binding binding

    Parser() {
        this.config = new CompilerConfiguration()
        config.scriptBaseClass = 'fr.kisled.dsl.DSLScript'
        config.addCompilationCustomizers(new ClosureCustomizer())
        this.shell = new GroovyShell(config)
        this.binding = new Binding()
        this.binding.setVariable("builder", new AppBuilder())
    }

    App parse(File file) {
        Script script = this.shell.parse(file)
        script.setBinding(this.binding)

        try {
            script.run()
        } catch (e) {
            println "Exception while parsing " + e.message
            e.printStackTrace(System.out)
        }

        println()

        String appname = file.name.replace(".groovy", "")

        App app = ((AppBuilder) this.binding.getVariable("builder")).build(appname)

        Validator validator = new Validator()

        if (!validator.validate(app))
            throw new AppValidationException()

        return app
    }
}
