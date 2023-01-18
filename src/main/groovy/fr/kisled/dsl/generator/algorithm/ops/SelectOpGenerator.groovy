package fr.kisled.dsl.generator.algorithm.ops

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.SelectOp

class SelectOpGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof SelectOp)
            return [
                    line.output_varname +
                            " = " +
                            line.input_varname +
                            line.range
            ]
        throw new IllegalArgumentException()
    }
}
