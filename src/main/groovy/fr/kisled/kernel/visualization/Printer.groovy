package fr.kisled.kernel.visualization

import fr.kisled.dsl.generator.visualization.PrinterGenerator
import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.kernel.CodeLine

@Generator(generator = PrinterGenerator.class)
class Printer extends CodeLine{
    def vars = [:]
//    String[] varnames
//
//    Printer(varnames) {
//        this.varnames = varnames
//    }
}
