package fr.kisled.kernel

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.ValidationGenerator

@Generator(generator = ValidationGenerator.class)
class Validation extends CodeLine {
    String algo
    String x
    String y
    String varname
    Map<String, Object> kwargs
}
