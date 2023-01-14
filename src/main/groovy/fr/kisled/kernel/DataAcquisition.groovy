package fr.kisled.kernel

class DataAcquisition extends Variable {
    String path

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        DataAcquisition that = (DataAcquisition) o
        return this.name == that.name && this.path == that.path
    }

    int hashCode() {
        return Objects.hash(this.name, this.path)
    }
}
