package fr.kisled.kernel.dataops

class Mapping extends DataOperation {
    String mapping

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Mapping that = (Mapping) o

        return super.equals(o) && mapping == that.mapping
    }
}
