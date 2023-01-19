package fr.kisled.dsl.generator.visualization

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Printer

class PrinterGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine toPrint) {
        if (toPrint instanceof Printer)
            return [
                    String.format(
                            "print(%s)",
                            String.join(", ",toPrint.varnames)
                    )
            ]
        throw new IllegalArgumentException()
    }
}
