package fr.kisled.dsl.generator

import fr.kisled.kernel.App

abstract class Generator {
    void generate(App app) {
        this.generate(app, System.out)
    }
    abstract void generate(App app, PrintStream output)
}
