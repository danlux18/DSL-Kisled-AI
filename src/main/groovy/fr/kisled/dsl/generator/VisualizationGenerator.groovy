package fr.kisled.dsl.generator

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Visualization

class VisualizationGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof Visualization)
            return [
                    String.format("plt.figure(figsize=(%d,%d))\n" +
                            "graph = plt.barh(%s,%s)\n" +
                            "plt.xlabel('%s')\n" +
                            "plt.ylabel('%s')\n",
                            line.names.length,
                            line.results.length,
                            line.names,
                            line.results,
                            line.xLabel,
                            line.yLabel
                    )
            ]
        throw new IllegalArgumentException()
    }
}
