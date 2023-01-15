package fr.kisled.kernel

class Variable extends NamedElement {
    Variable() { super }
    Variable(String name) {
        super
        setName(name)
    }

    @Override
    boolean equals(Object o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Variable var = (Variable) o

        return getName() == var.getName()
    }
}
