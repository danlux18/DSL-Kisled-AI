package fr.kisled.kernel.dataops

class ColumnDeletion extends DataOperation {
    String column

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        ColumnDeletion that = (ColumnDeletion) o

        return super.equals(o) && column == that.column
    }
}
