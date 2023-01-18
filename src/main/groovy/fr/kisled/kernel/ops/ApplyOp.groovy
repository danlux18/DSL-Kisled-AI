package fr.kisled.kernel.ops

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.ops.ApplyOpGenerator

@Generator(generator = ApplyOpGenerator.class)
class ApplyOp extends Op {
    String lambda
}
