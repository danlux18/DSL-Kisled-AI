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

    /**
     * Select a subpart of the current variable
     * Fixme: Check if the input variable is a Dataframe or a Set
     *
     * @param o The subset
     * @return The current builder to put it in a variable
     */
    def getAt(Object o) {
        println "Selection from $name $o (${o.class.name})"

        // Accessing from column index and not a list
        if (o instanceof Integer) {
            o = "[$o]"
        }

        this.selection = o

        return this
    }

    /**
     * Select a subset from column name
     * @param name The column
     * @return The current builder to put it in a variable
     */
    def getProperty(String name) {
        println "Selection from ${this.name} [\"$name\"]"

        if (name == ":")
            this.selection = "[:]"
        else
            this.selection = "[\"$name\"]"

        return this
    }

    /**
     * Put current operation result inside the given variable
     * @param varname the variable set by the operation
     */
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
