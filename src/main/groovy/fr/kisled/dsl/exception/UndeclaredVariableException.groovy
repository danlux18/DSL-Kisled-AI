package fr.kisled.dsl.exception

class UndeclaredVariableException extends Exception {
    String varname

    UndeclaredVariableException(String varname) {
        this.varname = varname
    }

    @Override
    String toString() {
        return "Variable " + varname + " undeclared"
    }
}
