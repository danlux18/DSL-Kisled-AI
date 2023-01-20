package fr.kisled.kernel.algorithm

import fr.kisled.dsl.generator.algorithm.AlgorithmGenerator
import fr.kisled.dsl.generator.algorithm.Generator

@Generator(generator =  AlgorithmGenerator.class)
class RandomForestClassifier extends Algorithm {
    Object n_estimators
    Object max_depth
}
