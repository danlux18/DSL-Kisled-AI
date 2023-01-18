package fr.kisled.kernel.algorithm

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.RandomForestGenerator

@Generator(generator =  RandomForestGenerator.class)
class RandomForest extends Algorithm {
    Object[] max_depth
    Object max_features
    Object min_samples_split
    Object min_samples_leaf
    boolean[] bootstrap
    String[] criterion
}
