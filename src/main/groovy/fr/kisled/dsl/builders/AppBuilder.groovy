package fr.kisled.dsl.builders

import fr.kisled.kernel.App
import fr.kisled.kernel.DataAcquisition

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

        app.setData(dataBuilders.collect {(DataAcquisition) it.build() })

        return app
    }
}
