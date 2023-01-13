package fr.kisled.dsl.builders

import fr.kisled.kernel.DataAcquisition
import fr.kisled.kernel.Variable

class DataBuilder {
    Variable var
    String name

    DataBuilder(String name) {
        this.name = name
    }

    def with(String path) {
        var = new DataAcquisition()

        var.setPath(path)
    }

    Variable build() {
        var.setName(name)
        return var
    }
}
