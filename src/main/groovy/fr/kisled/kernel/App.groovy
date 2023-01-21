package fr.kisled.kernel

import fr.kisled.kernel.visualization.Visualization

class App {
    final String name
    List<CodeLine> codeLines = []
    List<String> results
    List<String> resultsNames
    Visualization visualization

    App (String name) {
        this.name = name
    }
}
