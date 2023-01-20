package fr.kisled.dsl.generator.algorithm.ops

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.dsl.generator.algorithm.utils.RangeGenerator
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.ops.SelectOp
import fr.kisled.kernel.utils.Range

class SelectOpGenerator extends GeneratorStrategy  {
    private static String value2string(Object value, String inputVar) {
        if (value instanceof Integer)
            return "" + value
        else if (value instanceof String)
            return value == ":" ? value : "\"" + value + "\""
        else if (value instanceof Range)
            return String.format(
                    (new RangeGenerator()).toPython(value).get(0),
                    inputVar
            )
        else if (value instanceof List)
            return value.collect { value2string(it, inputVar) }.stream().reduce((a, b) ->
                   a + ", " + b).orElse(":")
        return ":"
    }

    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof SelectOp)
            return [
                    String.format(
                            "%s = %s[%s]",
                            line.output_varname,
                            line.input_varname,
                            value2string(line.range, line.input_varname)
                    ),
            ]
        throw new IllegalArgumentException()
    }
}
