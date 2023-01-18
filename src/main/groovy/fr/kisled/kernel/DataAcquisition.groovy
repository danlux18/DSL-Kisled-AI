package fr.kisled.kernel

import fr.kisled.dsl.generator.algorithm.DataAcquisitionGenerator
import fr.kisled.dsl.generator.algorithm.Generator

@Generator(generator = DataAcquisitionGenerator.class)
class DataAcquisition extends CodeLine {
    final String path
    final String varname

    DataAcquisition(String path, String varname) {
        this.path = path
        this.varname = varname
    }
}
