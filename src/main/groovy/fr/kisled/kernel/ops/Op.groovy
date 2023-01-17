package fr.kisled.kernel.ops

import fr.kisled.kernel.CodeLine

abstract class Op extends CodeLine {
    String input_varname
    String output_varname
}
