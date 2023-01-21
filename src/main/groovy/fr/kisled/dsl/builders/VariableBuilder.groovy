package fr.kisled.dsl.builders

import fr.kisled.dsl.builders.transformations.ClosureCustomizer
import fr.kisled.dsl.builders.utils.NoOp
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.ApplyOp
import fr.kisled.kernel.ops.DropColumnOp
import fr.kisled.kernel.ops.MappingOp
import fr.kisled.kernel.ops.SelectOp

class VariableBuilder extends CodeBuilder {
    String name
    VariableBuilder output
    Object selection = null
    String apply_lambda = null
    String dropped_column = null
    String mapping = null

    VariableBuilder(String name) {
        this.name = name
    }

    /**
     * Select a subpart of the current variable
     *
     * @param o The subset
     * @return The current builder to put it in a variable
     */
    def getAt(Object o) {
        this.selection = o

        return this
    }

    /**
     * Select a subset from column name
     * @param name The column
     * @return The current builder to put it in a variable
     */
    def getProperty(String name) {
        this.selection = name
        return this
    }

    /**
     * Put current operation result inside the given variable
     * @param varname the variable set by the operation
     */
    def rightShift(VariableBuilder varname) {
        this.output = varname
        return this
    }

    /**
     * Mapping operation
     * @param map the mapping to apply as a dict
     * @return The current builder to put it in a variable
     */
    def rightShift(Map map) {
        def final_val = (val) -> {
            if (val instanceof String)
                return "\"$val\""
            return val
        }

        this.mapping = "{${String.join(", ", map.collect {k, v -> "\"$k\": ${final_val(v)}"})}}"

        return this
    }

    /**
     * Apply plus function on Dataframe
     * @param val value to add to each value
     * @return The current builder to put it in a variable
     */
    def plus(val) {
        this.apply_lambda = "lambda x: x + $val"
        return this
    }

    /**
     * Apply minus function on Dataframe or remove column(s) if the parameter is a string or a list
     * @param val value to remove to each value
     * @return The current builder to put it in a variable
     */
    def minus(val) {
        if (val instanceof String)
            dropped_column = "\"$val\""
        else if (val instanceof ArrayList)
            dropped_column = "$val"
        else
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

    def apply(Closure cl) {
        String lambda = ClosureCustomizer.LAMBDAS.remove(0)

        this.apply_lambda = lambda

        return this
    }

    def methodMissing(String name, def args) {
        this.selection = name + "()"
        return this
    }

    @Override
    CodeLine build() {
        if (output == null) return new NoOp() // The operation is not properly formatted
        else if (apply_lambda != null)
            return new ApplyOp(input_varname: name, output_varname: output.getName(), lambda: apply_lambda)
        else if (dropped_column != null)
            return new DropColumnOp(input_varname: name, output_varname: output.getName(), dropped_column: dropped_column)
        else if (mapping != null)
            return new MappingOp(
                    input_varname: name,
                    input_select: selection,
                    output_varname: output.getName(),
                    output_select: output.getSelection(),
                    mapping: mapping
            )
        return new SelectOp(input_varname: name, output_varname: output.getName(), range: selection)
    }
}
