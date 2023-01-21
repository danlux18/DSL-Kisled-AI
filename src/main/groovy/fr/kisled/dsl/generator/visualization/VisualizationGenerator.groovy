package fr.kisled.dsl.generator.visualization

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Visualization

class VisualizationGenerator extends GeneratorStrategy {
    private static final String DATA = "data"
    List<String> results = []
    List<String> names = []

    private String results2string(Map dataSelection) {
        if (dataSelection.containsKey(DATA)) {
            Object select = dataSelection.get(DATA)

            if (select instanceof Integer) {
                return results.collect {it + "[" + select + "]"}
                        .stream().reduce((a, b) -> a + ", " + b).orElse("")
            }
            else if (select instanceof String) {
                if (select.contains("()"))
                    return results.collect {it + "." + select}
                            .stream().reduce((a, b) -> a + ", " + b).orElse("")
                else
                    return results.collect {it + "[\"" + select + "\"]"}
                            .stream().reduce((a, b) -> a + ", " + b).orElse("")
            }
        }
        return String.join(", ", results)
    }

    @Override
    List<String> toPython(CodeLine visualisation) {
        if (visualisation instanceof Visualization)
            return [
                    "chart_results = [" + results2string(visualisation.dataSelection) + "]",
                    "chart_names = [" + String.join(", ", names) + "]",
                    "plt.figure(figsize=(" + names.size() + ", " + results.size() + "))",
                    "graph = plt." + visualisation.type + "(chart_names, chart_results)",
                    "plt.xlabel('" + visualisation.xLabel + "')",
                    "plt.ylabel('" + visualisation.yLabel + "')",
                    "plt.title('" + visualisation.title + "')"
            ]
        throw new IllegalArgumentException()
    }
}
