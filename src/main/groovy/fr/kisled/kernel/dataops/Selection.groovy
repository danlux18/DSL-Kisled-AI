package fr.kisled.kernel.dataops

class Selection extends DataOperation {
    String filter

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Selection that = (Selection) o

        return super.equals(o) && filter == that.filter
    }
}
