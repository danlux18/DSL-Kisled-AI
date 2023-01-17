package fr.kisled.dsl

import fr.kisled.dsl.builders.AppBuilder


abstract class DSLScript extends Script {

    static class DataFrameBuilder {
        String path
        DataFrameBuilder(String path) { this.path = path }

        def getAt(Object... o) { return "getAt $this" }

        def rightShift(name) {
            println "Var $name = read $path"
            return name
        }
    }
    static class Variable {
        String name
        Variable (String name) { this.name = name }
        def plus(i) { return this }
        def minus(i) { return this }
    }

    def read(String path) {
        return ((AppBuilder) this.binding.getVariable("builder")).read(path)
    }

    def getProperty(String name) {
        if (name == "binding")
            return super.getBinding() // Return the bindings
        return name // Cast unknown property to String value
    }

    def propertyMissing(String name, value){
        println "Variable $name = $value"
        return new Variable(name)
    }

    def methodMissing(String name, def args) {
        println "Method $name ($args)"
        return name
    }
}
