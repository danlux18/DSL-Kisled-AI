package fr.kisled.dsl

import fr.kisled.dsl.builders.AppBuilder

abstract class DSLScript extends Script {
    def data(String name) {
        return ((AppBuilder) this.binding.getVariable("app")).data(name)
    }
}
