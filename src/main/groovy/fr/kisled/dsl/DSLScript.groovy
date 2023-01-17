package fr.kisled.dsl

import fr.kisled.dsl.builders.AppBuilder


abstract class DSLScript extends Script {
    AppBuilder builder = null

    def getProperty(String name) {
        if (name == "binding")
            return super.getBinding() // Return the bindings
        return builder.variable(name)
    }

    def methodMissing(String name, def args) {
        if (builder == null) {
            builder = ((AppBuilder) this.binding.getVariable("builder"));
        }
        println "Method $name ($args)"
        return builder.invokeMethod(name, args)
    }
}
