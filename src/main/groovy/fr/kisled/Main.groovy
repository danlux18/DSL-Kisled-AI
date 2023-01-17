package fr.kisled

import fr.kisled.dsl.Parser
import fr.kisled.dsl.generator.Generator
import fr.kisled.dsl.generator.python.PyGenerator
import fr.kisled.kernel.App

class Main {
    static void main(String... args) {
        Parser parser = new Parser()
        Generator generator = new PyGenerator()
        for (String arg : args) {
            App app = parser.parse(new File(arg))
            generator.generate(app)
        }
    }
}
