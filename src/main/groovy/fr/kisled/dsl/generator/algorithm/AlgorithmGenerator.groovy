package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.algorithm.Algorithm

class AlgorithmGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof Algorithm) {
            String var = line.output_varname
            String algo = line.getClass().getSimpleName()
            List excluded_props = ["class", "output_varname"]
            Map<String, Object> parameters = line.properties.findAll {k, v ->!excluded_props.contains(k)}

            return [
                    String.format("%s = %s(%s)", var, algo, params2string(parameters))
            ]
        }
        throw new IllegalArgumentException()
    }

    private static String params2string(Map<String, Object> parameters) {
        if (parameters.isEmpty())
            return ""

        List<String> duplicatedKeys = []
        parameters.forEach((k, v) -> {
            if (parameters.containsKey(k.toLowerCase()) && k != k.toLowerCase())
                duplicatedKeys.add(k.toLowerCase())
        })

        return parameters.findAll {k, v -> !duplicatedKeys.contains(k)}
                .collect {k, v -> k + " = " + value2string(v)}
                .stream().reduce((a, b) -> a + ", " + b)
                .orElse("")
    }

    private static String value2string(Object value) {
        if (value instanceof String)
            return "\"" + value + "\""
        else if (value instanceof Map) {
            return "{" +
                    value.collect {k, v -> "'" + k + "': " + value2string(v)}
                            .stream().reduce((a, b) -> a + ", " + b)
                            .orElse("") +
                    "}"
        }
        else if (value instanceof Collection) {
            return "[" +
                    value.collect {value2string(it)}
                            .stream().reduce((a, b) -> a + ", " + b)
                            .orElse("") +
                    "]"
        }
        else if (value instanceof Object[]) {
            return "[" +
                    String.join(", ", value.collect {value2string(it)}) + "]"
        }
        return "" + value
    }
}
