package fr.kisled.kernel.algorithm

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.RandomForestGenerator

@Generator(generator =  RandomForestGenerator.class)
class RandomForest extends Algorithm {
    Object n_estimators
    Object max_depth
}
