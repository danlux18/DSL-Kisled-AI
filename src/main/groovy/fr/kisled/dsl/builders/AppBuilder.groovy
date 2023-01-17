package fr.kisled.dsl.builders

import fr.kisled.kernel.App

class AppBuilder {
    List<CodeBuilder> lines = []

    AppBuilder() {
    }

    App build(String name) {
        App app = new App(name)

        app.getCodeLines().addAll(lines.collect {it.build()})

        return app
    }

    def read(String path) {
        println "Method read ($path)"
        CodeBuilder lineBuilder = new DataAcquisitionBuilder(path)
        lines.add(lineBuilder)
        return lineBuilder
    }

    Object invokeMethod(String name, Object args) {
        println "Catch method $name ($args)"
        return name
    }

    def propertyMissing(String name, value){
        println "Variable $name = $value"
        return name
    }
    static def $static_propertyMissing(String name) {
        println "Static variable $name"
        return name
    }

    def methodMissing(String name, def args) {
        println "Method $name ($args)"
        return name
    }
    static def $static_methodMissing(String name, Object args) {
        println "Static method $name ($args)"
        return name
    }
}
