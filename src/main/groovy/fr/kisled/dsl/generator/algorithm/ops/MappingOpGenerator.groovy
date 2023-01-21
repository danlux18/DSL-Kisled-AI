package fr.kisled.dsl.generator.algorithm.ops

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.MappingOp

class MappingOpGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof MappingOp)
            return [
                    line.output_varname + varSelect2String(line.output_select) +
                            " = " +
                            line.input_varname + varSelect2String(line.input_select) +
                            ".map(" +
                            line.mapping +
                            ")"
            ]
        throw new IllegalArgumentException()
    }

    private static String varSelect2String(String select) {
        if (select == null)
            return ""
        return "[\"" + select + "\"]"
    }
}
