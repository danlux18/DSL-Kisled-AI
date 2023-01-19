package fr.kisled.kernel.ops

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.ops.SelectOpGenerator

@Generator(generator = SelectOpGenerator.class)
class SelectOp extends Op {
    Object range // Can be an int, a string or a range
}
