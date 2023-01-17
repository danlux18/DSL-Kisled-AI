package fr.kisled.dsl.builders

import fr.kisled.dsl.builders.utils.NoOp
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.SelectOp

class VariableBuilder extends CodeBuilder {
    String name
    String output_varname
    String selection = null

    VariableBuilder(String name) {
        this.name = name
    }

    def getAt(Object o) {
        println "Selection from $name $o"
        this.selection = o

        return this
    }

    def rightShift(VariableBuilder varname) {
        println "Var ${varname.getName()} = $name"
        this.output_varname = varname.getName()
    }

    @Override
    CodeLine build() {
        if (output_varname == null) return new NoOp()
        return new SelectOp(input_varname: name, output_varname: output_varname, range: selection)
    }
}
