package fr.kisled.kernel.algorithm

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.KNNGenerator

@Generator(generator = KNNGenerator.class)
class KNN extends Algorithm {
    Object n_neighbors
    String[] algorithm
}
