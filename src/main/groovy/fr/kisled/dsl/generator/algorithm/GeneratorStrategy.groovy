package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine

abstract class GeneratorStrategy {
    abstract List<String> toPython(CodeLine line);
}
