package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.algorithm.Algorithm
import fr.kisled.kernel.algorithm.KNN
import fr.kisled.kernel.algorithm.RandomForest

class AlgorithmBuilder extends CodeBuilder {
    Algorithm algorithm
    String varname

    def KNN(n_neighbors, algorithm) {
        this.algorithm = new KNN(n_neighbors: n_neighbors, algorithm: algorithm)
        return this
    }

    def RandomForest(n_estimators, max_depth) {
        algorithm = new RandomForest(
                n_estimators: n_estimators,
                max_depth: max_depth
        )
        return this
    }

    def methodMissing(String name, def args) {
        println "Unknown algorithm $name ($args)"
        return this
    }

    def rightShift(VariableBuilder v) {
        this.varname = v.getName()
    }

    @Override
    CodeLine build() {
        algorithm.setOutput_varname(varname)
        return algorithm
    }
}
