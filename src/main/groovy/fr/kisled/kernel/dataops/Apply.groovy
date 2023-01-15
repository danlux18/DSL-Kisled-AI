package fr.kisled.kernel.dataops

class Apply extends DataOperation {
    String operation

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Apply that = (Apply) o

        return super.equals(o) && operation == that.operation
    }
}
