package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Printer

class PrinterBuilder extends CodeBuilder {
    String[] varnames

    PrinterBuilder(varnames){
        this.varnames = varnames
    }

    @Override
    CodeLine build() {
        return new Printer(varnames)
    }
}
