package fr.kisled.kernel.visualization

import fr.kisled.dsl.generator.VisualizationGenerator
import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.kernel.CodeLine

@Generator(generator = VisualizationGenerator.class)
class Visualization extends CodeLine {
    String[] names
    Float[] results
    String xLabel
    String yLabel

    Visualization(String[] names, Float[] results, String xLabel, String yLabel) {
        this.names = names
        this.results = results
        this.xLabel = xLabel
        this.yLabel = yLabel
    }
}
