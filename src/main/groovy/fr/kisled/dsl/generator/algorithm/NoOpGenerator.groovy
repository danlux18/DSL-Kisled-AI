package fr.kisled.dsl.generator.algorithm

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine

class NoOpGenerator extends GeneratorStrategy {
    @Override
    String[] toPython(CodeLine noop) {
        return new String[0]
    }
}
