package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.DataAcquisition

class DataAcquisitionGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof DataAcquisition)
            return [ line.varname + " = pd.read_csv(\"" + line.path + "\"" + indexCol2String(line.index_col) + ")" ]
        throw new IllegalArgumentException()
    }

    private static String indexCol2String(Object index_col) {
        if (index_col instanceof String) {
            return index_col.isBlank() ? "" : ", index_col = \"" + index_col + "\""
        }
        return ", index_col = " + index_col
    }
}
