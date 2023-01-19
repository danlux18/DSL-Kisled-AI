package fr.kisled.dsl

import fr.kisled.dsl.builders.AppBuilder


abstract class DSLScript extends Script {
    AppBuilder builder = null

    /**
     * Tells the builder that it may be a variable declaration or usage
     * @param name the name of the searched property
     * @return a variable builder
     */
    def getProperty(String name) {
        if (name == "binding")
            return super.getBinding() // Return the bindings
        if (builder == null) {
            builder = ((AppBuilder) this.binding.getVariable("builder"));
        }
        return builder.variable(name)
    }

    /**
     * Calls the method on app builder
     * @param name the name of the method
     * @param args the args of the method
     * @return the method result
     */
    def methodMissing(String name, def args) {
        if (builder == null) {
            builder = ((AppBuilder) this.binding.getVariable("builder"));
        }
        return builder.invokeMethod(name, args)
    }
}
