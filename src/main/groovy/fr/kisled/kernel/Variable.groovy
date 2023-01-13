package fr.kisled.kernel

class Variable extends NamedElement {
    Variable() { super }
    Variable(String name) {
        super
        setName(name)
    }
}
