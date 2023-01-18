package fr.kisled.dsl.generator.algorithm.ops

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.MappingOp

class MappingOpGenerator extends GeneratorStrategy {
    @Override
    String[] toPython(CodeLine line) {
        if (line instanceof MappingOp)
            return [
                    line.output_varname +
                            " = " +
                            line.input_varname +
                            ".map(" +
                            line.mapping +
                            ")"
            ]
        throw new IllegalArgumentException()
    }
}
