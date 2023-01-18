package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.algorithm.KNN

class KNNGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine algo) {
        if (algo instanceof KNN)
            return [
                    String.format(
                            "%s = KNeighborsClassifier(n_neighbors=%s, algorithm=%s)",
                            algo.output_varname, algo.n_neighbors, String.join(', ', algo.algorithm.collect {it instanceof String ? "\"$it\"" : "$it"})
                    )
            ]
        throw new IllegalArgumentException()
    }
}
