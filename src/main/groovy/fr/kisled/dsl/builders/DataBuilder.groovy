package fr.kisled.dsl.builders

import fr.kisled.kernel.DataAcquisition
import fr.kisled.kernel.Variable
import fr.kisled.kernel.dataops.Selection

class DataBuilder {
    Object var
    String name

    DataBuilder(String name) {
        this.name = name
    }

    def with(String path) {
        var = new DataAcquisition()
        var.setName(name)
        var.setPath(path)
    }

    def from(String inputVar) {
        [
                select: {
                    String filter -> {
                        var = new Selection()
                        var.setInput(new Variable(inputVar))
                        var.setOutput(new Variable(name))
                        var.setFilter(filter)
                    }
                }
        ]
    }

    Object build() {
        return var
    }
}
