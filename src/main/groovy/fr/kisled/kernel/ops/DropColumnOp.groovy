package fr.kisled.kernel.ops

import fr.kisled.kernel.CodeLine

class DropColumnOp extends CodeLine {
    String input_varname
    String output_varname
    String dropped_column
}
