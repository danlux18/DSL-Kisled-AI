package fr.kisled.dsl.builders

import fr.kisled.dsl.builders.transformations.ClosureCustomizer
import fr.kisled.dsl.builders.utils.NoOp
import fr.kisled.kernel.App
import fr.kisled.kernel.Validation
import fr.kisled.kernel.utils.Range
import fr.kisled.kernel.visualization.Visualization

class AppBuilder {
    List<CodeBuilder> lines = [] // lines of code in the order wanted by the user
    VisualizationBuilder visualization = null

    AppBuilder() {
    }

    def variable(String name) {
        CodeBuilder builder = new VariableBuilder(name)
        lines.add(builder)
        return builder
    }

    def read(String path, Object index_col = '') {
        CodeBuilder lineBuilder = new DataAcquisitionBuilder(path, index_col)
        lines.add(lineBuilder)
        return lineBuilder
    }

    def chart(String title, String xLabel, String yLabel, String type = 'plot', VariableBuilder builder = null) {
        visualization = new VisualizationBuilder(title, xLabel, yLabel, type, builder)
        return visualization
    }

    /**
     * Build a range where start, stop and step are optional.
     * If the start is equals to stop, then the range describe all value in a set
     */
    static def r(Integer start=null, Integer stop=null, step=1) {
        return new Range(start: start, stop: stop, step: step)
    }

    static def btw(start, stop) {
        if (start instanceof Integer && stop instanceof Integer)
            return "randint($start, $stop)"
        return "random($start, $stop)"
    }

    static def choice(array) {
        return "choice($array)"
    }

    def disp(VariableBuilder... variables) {
        CodeBuilder builder = new PrinterBuilder(vars: variables)
        lines.add(builder)
        return builder
    }

    def validate(def kwargs, VariableBuilder algo, VariableBuilder X_train, VariableBuilder Y_train) {
        CodeBuilder builder = new ValidationBuilder(algo, X_train, Y_train, kwargs)
        lines.add(builder)
        return builder
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

    // === Utils ===

    def methodMissing(String name, def args) {
        println "Unknown method $name called"
    }

    App build(String name) {
        App app = new App(name)

        app.getCodeLines().addAll(lines.collect {it.build()}.findAll {!(it instanceof NoOp)})

        app.setResults(
                app.getCodeLines()
                        .collect {it instanceof Validation ? it.varname : null}
                        .findAll { it != null }
        )

        app.setVisualization((Visualization) visualization.build())

        app.setResultsNames(
                app.getCodeLines()
                        .collect {it instanceof Validation ? it.algo : null}
                        .findAll { it != null }
        )

        return app
    }
}
