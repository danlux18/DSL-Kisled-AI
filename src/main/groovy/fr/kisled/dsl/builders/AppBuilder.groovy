package fr.kisled.dsl.builders

import fr.kisled.dsl.exception.UndeclaredVariableException
import fr.kisled.kernel.App
import fr.kisled.kernel.DataAcquisition
import fr.kisled.kernel.dataops.DataOperation

class AppBuilder {
    // Register the existing variables to check validity on data operation
    List<String> variables
    List<DataBuilder> dataBuilders

    AppBuilder() {
        variables = new ArrayList<>()
        dataBuilders = new ArrayList<>()
    }

    def data(String name) {
        variables.add(name)
        DataBuilder builder = new DataBuilder(name)

        dataBuilders.add(builder)

        return builder
    }

    App build() {
        App app = new App()

        List<DataAcquisition> dataAcquisitions = new ArrayList<>()
        List<DataOperation> dataOperations = new ArrayList<>()

        for (DataBuilder builder : dataBuilders) {
            Object var = builder.build()

            if (var instanceof DataAcquisition)
                dataAcquisitions.add(var)
            else if (var instanceof DataOperation) {
                if (!variables.contains(var.getInput().getName()))
                    throw new UndeclaredVariableException(var.getInput().getName())

                variables.add(var.getOutput().getName())
                dataOperations.add(var)
            }
        }

        app.setData(dataAcquisitions)
        app.setDataOperations(dataOperations)

        return app
    }
}
