package fr.kisled.dsl.generator.algorithm

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.DataAcquisition

class DataAcquisitionGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof DataAcquisition)
            return [ line.varname + " = pd.read_csv(\"" + line.path + "\")" ]
        throw new IllegalArgumentException();
    }
}
