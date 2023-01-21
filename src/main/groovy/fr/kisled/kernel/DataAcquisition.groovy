package fr.kisled.kernel

import fr.kisled.dsl.generator.algorithm.DataAcquisitionGenerator
import fr.kisled.dsl.generator.algorithm.Generator

@Generator(generator = DataAcquisitionGenerator.class)
class DataAcquisition extends CodeLine {
    final String path
    Object index_col
    final String varname

    DataAcquisition(String path, String varname, Object index_col) {
        this.path = path
        this.varname = varname
        this.index_col = index_col
    }
}
