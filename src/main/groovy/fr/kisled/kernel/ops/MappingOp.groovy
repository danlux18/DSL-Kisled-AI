package fr.kisled.kernel.ops

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.ops.MappingOpGenerator

@Generator(generator = MappingOpGenerator.class)
class MappingOp extends Op {
    String mapping
}
