package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine

import fr.kisled.kernel.visualization.Visualization

class VisualizationBuilder extends CodeBuilder {
    Visualization visualization

    VisualizationBuilder(String title, String xLabel, String yLabel, String type) {
        this.visualization = new Visualization(title, xLabel, yLabel, type)
    }

    @Override
    CodeLine build() {
        return visualization
    }
}
