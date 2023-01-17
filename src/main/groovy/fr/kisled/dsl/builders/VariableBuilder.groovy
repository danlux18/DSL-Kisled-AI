package fr.kisled.dsl.builders

import fr.kisled.dsl.builders.utils.NoOp
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.ApplyOp
import fr.kisled.kernel.ops.SelectOp

class VariableBuilder extends CodeBuilder {
    String name
    String output_varname
    String selection = null
    String apply_lambda = null

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
//        println "Selection from $name $o (${o.class.name})"
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
//        println "Selection from ${this.name} [\"$name\"]"
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
//        println "Var ${varname.getName()} = $name"
        this.output_varname = varname.getName()
    }

    /**
     * Apply plus function on Dataframe
     * @param val value to add to each value
     * @return The current builder to put it in a variable
     */
    def plus(val) {
//        println "Apply op `+ $val`"
        this.apply_lambda = "lambda x: x + $val"
        return this
    }

    /**
     * Apply minus function on Dataframe
     * @param val value to remove to each value
     * @return The current builder to put it in a variable
     */
    def minus(val) {
        this.apply_lambda = "lambda x: x - $val"
        return this
    }

    def multiply(val) {
        this.apply_lambda = "lambda x: x * $val"
        return this
    }

    def power(val) {
        this.apply_lambda = "lambda x: x ** $val"
        return this
    }

    def div(val) {
        if (val == 0) {
            throw new ArithmeticException("Division by zero")
        }
        this.apply_lambda = "lambda x: x / $val"
        return this
    }

    @Override
    CodeLine build() {
        if (output_varname == null) return new NoOp() // The operation is not properly formatted
        else if (apply_lambda != null)
            return new ApplyOp(input_varname: name, output_varname: output_varname, lambda: apply_lambda)
        return new SelectOp(input_varname: name, output_varname: output_varname, range: selection)
    }
}
