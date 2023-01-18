package fr.kisled.dsl.generator.algorithm.ops

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.ApplyOp

class ApplyOpGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof ApplyOp)
            return [
                    line.output_varname +
                            " = " +
                            line.input_varname +
                            ".apply(" +
                            line.lambda +
                            ")"
            ]
        throw new IllegalArgumentException()
    }
}
