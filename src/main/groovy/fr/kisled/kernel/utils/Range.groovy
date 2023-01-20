package fr.kisled.kernel.utils

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.utils.RangeGenerator
import fr.kisled.kernel.CodeLine

@Generator(generator = RangeGenerator.class)
class Range extends CodeLine {
    Integer start = null // null means missing
    Integer stop = null // null means missing
    int step = 1
}
