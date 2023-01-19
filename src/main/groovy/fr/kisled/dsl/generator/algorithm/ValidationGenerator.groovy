package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.Validation

class ValidationGenerator extends GeneratorStrategy {
    private static String value2String(Object value) {
        if (value instanceof String)
            return "\"" + value + "\""
        else if (value instanceof Collection) {
            return "[" +
                    value.stream().reduce((a, b) -> value2String(a) + ", " + value2String(b)).orElse("") +
                    "]"
        }
        else if (value instanceof Map) {
            return "{" + value.collect {
                k, v -> "'" + k + "':" + value2String(v)
            }.stream().reduce((a, b) -> a + ", " + b).orElse("") + "}"
        }
        return "" + value
    }

    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof Validation)
            return [
                    String.format(
                            "%s = cross_val_score(%s, %s, %s, %s)",
                            line.varname,
                            line.algo,
                            line.x,
                            line.y,
                            String.join(
                                    ", ",
                                    line.kwargs.collect {k, v ->
                                        "" + k + " = " + value2String(v)
                                    }
                            )
                    )
            ]
        throw new IllegalArgumentException()
    }
}
