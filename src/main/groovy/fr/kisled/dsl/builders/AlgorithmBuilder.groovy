package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.algorithm.Algorithm
import fr.kisled.kernel.algorithm.DecisionTreeClassifier
import fr.kisled.kernel.algorithm.GaussianNB
import fr.kisled.kernel.algorithm.GradientBoostingClassifier
import fr.kisled.kernel.algorithm.KNeighborsClassifier
import fr.kisled.kernel.algorithm.LinearSVC
import fr.kisled.kernel.algorithm.LogisticRegression
import fr.kisled.kernel.algorithm.MLPClassifier
import fr.kisled.kernel.algorithm.RandomForestClassifier

class AlgorithmBuilder extends CodeBuilder {
    Algorithm algorithm
    String varname

    def KNN(n_neighbors, algorithm) {
        this.algorithm = new KNeighborsClassifier(n_neighbors: n_neighbors, algorithm: algorithm)
        return this
    }

    def RandomForest(n_estimators, max_depth) {
        algorithm = new RandomForestClassifier(
                n_estimators: n_estimators,
                max_depth: max_depth
        )
        return this
    }

    def LogisticRegression(max_iter) {
        algorithm = new LogisticRegression(max_iter: max_iter)
        return this
    }

    def GaussianNB() {
        algorithm = new GaussianNB()
        return this
    }

    def DecisionTreeClassifier() {
        algorithm = new DecisionTreeClassifier()
        return this
    }

    def GradientBoostingClassifier(n_estimators) {
        algorithm = new GradientBoostingClassifier(n_estimators: n_estimators)
        return this
    }

    def LinearSVC(C) {
        algorithm = new LinearSVC(C: C)
        return this
    }

    def MLPClassifier(max_iter) {
        algorithm = new MLPClassifier(max_iter: max_iter)
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
