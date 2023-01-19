package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Visualization

class VisualizationBuilder extends CodeBuilder {
    String[] names
    Float[] results
    String xLabel
    String yLabel

    VisualizationBuilder(List<String> names, List<Number> results, String xLabel, String yLabel) {
        this.names = names.toArray(new String[0])
        this.results = results
        this.xLabel = xLabel
        this.yLabel = yLabel
    }

    @Override
    CodeLine build() {
        return new Visualization(names, results, xLabel, yLabel)
    }
}
