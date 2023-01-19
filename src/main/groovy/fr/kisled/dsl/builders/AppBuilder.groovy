package fr.kisled.dsl.builders

import fr.kisled.dsl.builders.utils.NoOp
import fr.kisled.kernel.App

class AppBuilder {
    List<CodeBuilder> lines = [] // lines of code in the order wanted by the user

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

    /**
     * Build a range where start, stop and step are optional.
     * If the start is equals to stop, then the range describe all value in a set
     */
    static def r(start=0, stop=0, step=1) {
        if (start == stop) {
            return ":"
        }
        return step == 1 ? "$start:$stop" : "range($start, $stop, $step)"
    }

    static def btw(start, stop) {
        return "randint($start, $stop)"
    }

    static def choice(array) {
        return "choice($array)"
    }

    def KNN(def hparams) {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.KNN(hparams['n_neighbors'], hparams['algorithm'])
    }

    def RandomForest(def hparams) {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.RandomForest(
                hparams['n_estimators'],
                hparams['max_depth']
        )
    }

    def validate(def kwargs, VariableBuilder algo, VariableBuilder X_train, VariableBuilder Y_train) {
        CodeBuilder builder = new ValidationBuilder(algo, X_train, Y_train, kwargs)
        lines.add(builder)
        return builder
    }

    def methodMissing(String name, def args) {
        println "Unknown method $name called"
    }

    App build(String name) {
        App app = new App(name)

        app.getCodeLines().addAll(lines.collect {it.build()}.findAll {!(it instanceof NoOp)})

        return app
    }
}
