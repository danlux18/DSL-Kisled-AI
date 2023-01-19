package fr.kisled.dsl.generator.visualization

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Visualization

class VisualizationGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine visualisation) {
        if (visualisation instanceof Visualization)
            return [
                    String.format(
                            "plt.xlabel('%s')\n" +
                            "plt.ylabel('%s')\n" +
                            "plt.title('%s')\n",
                            visualisation.xLabel,
                            visualisation.yLabel,
                            visualisation.title
                    )
            ]
        throw new IllegalArgumentException()
    }
}
