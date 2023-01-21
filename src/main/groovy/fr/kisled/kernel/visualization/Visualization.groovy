package fr.kisled.kernel.visualization

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.visualization.VisualizationGenerator
import fr.kisled.kernel.CodeLine

@Generator(generator = VisualizationGenerator.class)
class Visualization extends CodeLine {
    String title
    String xLabel
    String yLabel
    String type
    Map dataSelection

    Visualization(String title, String xLabel, String yLabel, String type, Map dataSelection) {
        this.title = title
        this.xLabel = xLabel
        this.yLabel = yLabel
        this.type = type
        this.dataSelection = dataSelection
    }
}
