package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.algorithm.KNN

class KNNGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine algo) {
        if (algo instanceof KNN)
            return [ "KNN ()" ]
        throw new IllegalArgumentException()
    }
}
