package fr.kisled

import fr.kisled.dsl.Parser
import fr.kisled.dsl.generator.Generator
import fr.kisled.dsl.generator.jupyter.JupyterGenerator
import fr.kisled.dsl.generator.python.PyGenerator
import fr.kisled.kernel.App

class Main {
    static void main(String... args) {
        Parser parser = new Parser()
        Generator generator = new JupyterGenerator()
        for (String arg : args) {
            App app = parser.parse(new File(arg))

            File result = new File("../${app.name}.ipynb")
            PrintStream output = new PrintStream(result)

            generator.generate(app, output)

            output.close()
        }
    }
}
