package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.DataAcquisition

class DataAcquisitionBuilder extends CodeBuilder {
    String path
    Object index_col
    String varname

    DataAcquisitionBuilder(String path, Object index_col) {
        if (!path.endsWith(".csv"))
            throw new IllegalArgumentException("\"read\" method only takes CSV files as path parameter")
        this.path = path
        this.index_col = index_col
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
        return new DataAcquisition(path, varname, index_col)
    }
}
