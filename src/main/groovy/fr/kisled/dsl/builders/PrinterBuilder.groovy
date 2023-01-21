package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Printer

class PrinterBuilder extends CodeBuilder {
    List<VariableBuilder> vars = []

    @Override
    CodeLine build() {
        return new Printer(vars: vars.stream().map((v) -> {
            Map m = new LinkedHashMap()
            m.put(v.getName(), v.getSelection())
            return m
        }).reduce((a, b) -> a + b).orElse([:]))
    }
}
