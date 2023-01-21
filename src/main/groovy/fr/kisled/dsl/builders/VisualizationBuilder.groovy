package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine

import fr.kisled.kernel.visualization.Visualization

class VisualizationBuilder extends CodeBuilder {
    Visualization visualization

    VisualizationBuilder(String title, String xLabel, String yLabel, String type, VariableBuilder builder) {
        Map dataSelection = [:]
        if (builder != null)
            dataSelection.put(builder.getName(), builder.getSelection())
        this.visualization = new Visualization(title, xLabel, yLabel, type, dataSelection)
    }

    @Override
    CodeLine build() {
        return visualization
    }
}
