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
        println "randint($start, $stop)"
        return "randint($start, $stop)"
    }

    static def choice(array) {
        println "choice($array)"
        return "choice($array)"
    }

    // === Algorithms ===

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

    def LogisticRegression(def hparams) {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.LogisticRegression(hparams['max_iter'])
    }

    def GaussianNB() {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.GaussianNB()
    }

    def DecisionTreeClassifier() {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.DecisionTreeClassifier()
    }

    def GradientBoostingClassifier(def hparams) {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.GradientBoostingClassifier(hparams['n_estimators'])
    }

    def LinearSVC(def hparams) {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.LinearSVC(hparams['C'])
    }

    def MLPClassifier(def hparams) {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.MLPClassifier(hparams['max_iter'])
    }

    def VotingClassifier(def hparams) {
        CodeBuilder builder = new AlgorithmBuilder()
        lines.add(builder)
        return builder.VotingClassifier(hparams['estimators'], hparams['voting'])
    }

    // === Utils ===

    def methodMissing(String name, def args) {
        println "Unknown method $name called"
    }

    App build(String name) {
        App app = new App(name)

        app.getCodeLines().addAll(lines.collect {it.build()}.findAll {!(it instanceof NoOp)})

        return app
    }
}
