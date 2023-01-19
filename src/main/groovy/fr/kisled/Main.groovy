package fr.kisled

import fr.kisled.dsl.AppValidationException
import fr.kisled.dsl.Parser
import fr.kisled.dsl.generator.Generator
import fr.kisled.dsl.generator.jupyter.JupyterGenerator
import fr.kisled.dsl.generator.python.PyGenerator
import fr.kisled.kernel.App
import groovy.cli.picocli.CliBuilder

class Main {
    static void main(String... args) {
        def cli = new CliBuilder(name: "kisled-ai")
        def options = new OptionClass()
        cli.parseFromInstance(options, args)

        Parser parser = new Parser()
        Generator generator = options.extension == 'py' ? new PyGenerator() : new JupyterGenerator()

        boolean output_defined = options.output != null && options.output != ""
        File result = null;
        if (output_defined)
            result = new File(options.output)

        PrintStream output = output_defined ? new PrintStream(result) : System.out

        for (String arg : options.sources) {
            try {
                App app = parser.parse(new File(arg))

                generator.generate(app, output)
            } catch (AppValidationException ignored) {
                System.err.printf ("Parsing of %s failed\n", arg)
            }
        }

        if (output_defined)
            output.close()
    }
}
