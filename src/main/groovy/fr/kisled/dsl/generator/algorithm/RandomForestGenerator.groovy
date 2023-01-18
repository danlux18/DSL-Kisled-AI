package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.algorithm.RandomForest

class RandomForestGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine algo) {
        if (algo instanceof RandomForest)
            return [ "RandomForest ()" ]
        throw new IllegalArgumentException()
    }
}
