package fr.kisled.dsl.builders

import fr.kisled.kernel.DataAcquisition
import fr.kisled.kernel.Variable
import fr.kisled.kernel.dataops.Apply
import fr.kisled.kernel.dataops.ColumnDeletion
import fr.kisled.kernel.dataops.Mapping
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
                apply: {
                    String operation -> {
                        var = new Apply()
                        var.setInput(new Variable(inputVar))
                        var.setOutput(new Variable(name))
                        var.setOperation(operation)
                    }
                },
                delete: {
                    String column -> {
                        var = new ColumnDeletion()
                        var.setInput(new Variable(inputVar))
                        var.setOutput(new Variable(name))
                        var.setColumn(column)
                    }
                },
                map: {
                    String mapping -> {
                        var = new Mapping()
                        var.setInput(new Variable(inputVar))
                        var.setOutput(new Variable(name))
                        var.setMapping(mapping)
                    }
                },
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
