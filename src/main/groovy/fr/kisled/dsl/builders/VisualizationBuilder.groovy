package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Visualization

class VisualizationBuilder extends CodeBuilder {
    String[] names
    Float[] results
    String xLabel
    String yLabel

    VisualizationBuilder(List<String> names, String xLabel, String yLabel) {
        this.names = names.toArray(new String[0])
        this.results = [1,2,3,4]
        this.xLabel = xLabel
        this.yLabel = yLabel
    }

    @Override
    CodeLine build() {
        return new Visualization(names, results, xLabel, yLabel)
    }
}
