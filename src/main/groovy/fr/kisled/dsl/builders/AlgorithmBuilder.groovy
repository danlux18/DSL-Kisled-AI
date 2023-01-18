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
        println "KNN ($n_neighbors, $algorithm)"
        return this
    }

    def RandomForest(max_depth, max_features, min_samples_split, min_samples_leaf, bootstrap, criterion) {
        algorithm = new RandomForest(
                max_depth: max_depth,
                max_features: max_features,
                min_samples_split: min_samples_split,
                min_samples_leaf: min_samples_leaf,
                bootstrap: bootstrap,
                criterion: criterion
        )
        println "RandomForest ($max_depth, $max_features, $min_samples_split, $min_samples_leaf, $bootstrap, $criterion)"
        return this
    }

    def methodMissing(String name, def args) {
        println "Unknown algorithm $name ($args)"
        return this
    }

    def rightShift(VariableBuilder var) {
        this.varname = var.name
    }

    @Override
    CodeLine build() {
        algorithm.setOutput_varname(varname)
        return algorithm
    }
}
