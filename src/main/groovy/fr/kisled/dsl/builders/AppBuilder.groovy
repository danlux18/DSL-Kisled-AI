package fr.kisled.dsl.builders

import fr.kisled.kernel.App
import fr.kisled.kernel.DataAcquisition
import fr.kisled.kernel.Variable

class AppBuilder {
    // Register the existing variables to check validity on data operation
    Map<String, Variable> variables
    List<DataAcquisition> dataAcquisitions

    AppBuilder() {
        variables = new HashMap<>()
        dataAcquisitions = new ArrayList<>()
    }

    def data(String name) {
        [with: {
            String path -> {
                Variable var = new DataAcquisition()
                var.setName(name)
                var.setPath(path)
                dataAcquisitions.add (var)
                variables.put (name, var)
            }
        }
        ]
    }

    App build() {
        App app = new App()

        app.setData(dataAcquisitions)

        return app
    }
}
