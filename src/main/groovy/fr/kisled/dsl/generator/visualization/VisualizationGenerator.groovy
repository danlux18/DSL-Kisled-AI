package fr.kisled.dsl.generator.visualization

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Visualization

class VisualizationGenerator extends GeneratorStrategy {
    List<String> results = []
    List<String> names = []

    @Override
    List<String> toPython(CodeLine visualisation) {
        if (visualisation instanceof Visualization)
            return [
                    "chart_results = [" + String.join(", ", results) + "]",
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
