package fr.kisled.dsl.builders

import fr.kisled.dsl.builders.utils.NoOp
import fr.kisled.kernel.App

class AppBuilder {
    List<CodeBuilder> lines = [] // TODO

    AppBuilder() {
    }

    def variable(String name) {
        CodeBuilder builder = new VariableBuilder(name)
        lines.add(builder)
        return builder
    }

    def read(String path) {
        CodeBuilder lineBuilder = new DataAcquisitionBuilder(path)
        lines.add(lineBuilder)
        return lineBuilder
    }

    static def r(start=0, stop=0, step=1) {
        if (start == stop) {
            return ":"
        }
        return step == 1 ? "$start:$stop" : "range($start, $stop, $step)"
    }

    App build(String name) {
        App app = new App(name)

        app.getCodeLines().addAll(lines.collect {it.build()}.findAll {!(it instanceof NoOp)})

        return app
    }
}
