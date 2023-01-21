package fr.kisled.kernel.ops

import fr.kisled.kernel.CodeLine

abstract class Op extends CodeLine {
    String input_varname
    String input_select = null
    String output_varname
    String output_select = null
}
