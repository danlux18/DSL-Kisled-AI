package fr.kisled.dsl.generator.algorithm.utils

import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.utils.Range

class RangeGenerator extends GeneratorStrategy {
    @Override
    List<String> toPython(CodeLine line) {
        if (line instanceof Range) {
            String select = ""

            if (line.step != 1) {
                select += "slice(start = "
                select += (line.start == null ? 0 : line.start)
                select += ", end = "
                select += (line.stop == null ? "len(%s)" : line.stop)
                select += ", step = "
                select += line.step
                select += ")"
            }
            else {
                if (line.start != null)
                    select += line.start
                select += ":"
                if (line.stop != null) {
                    select += line.stop
                }
            }
            return [ select ]
        }
        throw new IllegalArgumentException()
    }
}
