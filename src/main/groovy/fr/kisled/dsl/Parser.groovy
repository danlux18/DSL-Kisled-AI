package fr.kisled.dsl

import fr.kisled.dsl.builders.AppBuilder
import fr.kisled.kernel.App
import org.codehaus.groovy.control.CompilerConfiguration

class Parser {
    CompilerConfiguration config
    GroovyShell shell
    Binding binding

    Parser() {
        this.config = new CompilerConfiguration()
        config.scriptBaseClass = 'fr.kisled.dsl.DSLScript'
        this.shell = new GroovyShell(config)
        this.binding = new Binding()
        this.binding.setVariable("app", new AppBuilder())
    }

    App parse(File file) {
        Script script = this.shell.parse(file)
        script.setBinding(this.binding)

        script.run()

        return ((AppBuilder) this.binding.getVariable("app")).build()
    }
}
