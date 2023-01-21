package fr.kisled.dsl.generator.algorithm.ops

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.DropColumnOp

class DropColumnOpGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof DropColumnOp)
            return [
                    line.output_varname +
                            " = " +
                            line.input_varname +
                            ".drop(" +
                            line.dropped_column +
                            ", axis = 1)"
            ]
        throw new IllegalArgumentException()
    }
}
