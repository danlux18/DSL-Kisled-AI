package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.DataAcquisition

class DataAcquisitionBuilder extends CodeBuilder {
    String path
    String varname

    DataAcquisitionBuilder(String path) {
        this.path = path
    }

    /**
     * Put acquired data into a variable
     * @param varname variable name
     */
    def rightShift(VariableBuilder varname) {
        this.varname = varname.getName()
    }

    @Override
    CodeLine build() {
        return new DataAcquisition(path, varname)
    }
}
