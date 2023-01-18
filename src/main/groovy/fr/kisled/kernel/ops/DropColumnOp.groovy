package fr.kisled.kernel.ops

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.ops.DropColumnOpGenerator

@Generator(generator = DropColumnOpGenerator.class)
class DropColumnOp extends Op {
    String dropped_column
}
