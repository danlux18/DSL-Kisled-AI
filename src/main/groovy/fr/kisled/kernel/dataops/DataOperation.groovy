package fr.kisled.kernel.dataops

import fr.kisled.kernel.Variable

abstract class DataOperation {
    Variable input
    Variable output

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        DataOperation that = (DataOperation) o

        if (input != that.input) return false
        if (output != that.output) return false

        return true
    }

    int hashCode() {
        int result
        result = (input != null ? input.hashCode() : 0)
        result = 31 * result + (output != null ? output.hashCode() : 0)
        return result
    }
}
