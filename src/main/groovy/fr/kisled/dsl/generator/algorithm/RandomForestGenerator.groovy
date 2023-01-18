package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.algorithm.RandomForest

class RandomForestGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine algo) {
        if (algo instanceof RandomForest)
            return [
                    String.format(
                            "%s = RandomForestClassifier(n_estimators=%s, max_depth=%s)",
                            algo.output_varname,
                            algo.n_estimators,
                            algo.max_depth
                    )
            ]
        throw new IllegalArgumentException()
    }
}
