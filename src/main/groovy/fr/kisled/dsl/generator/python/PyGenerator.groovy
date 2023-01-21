package fr.kisled.dsl.generator.python

import fr.kisled.dsl.generator.algorithm.Generator
import fr.kisled.dsl.generator.algorithm.GeneratorStrategy
import fr.kisled.dsl.generator.visualization.VisualizationGenerator
import fr.kisled.kernel.App
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.visualization.Visualization

class PyGenerator extends fr.kisled.dsl.generator.Generator {
    @Override
    void generate(App app, PrintStream output) {
        output.println("\"\"\" App " + app.getName() + " \"\"\"")

        generateImports(output)

        output.println()

        for (CodeLine line : app.getCodeLines()) {
            generateCodeLine(line, output)
        }

        generateChart(app.getVisualization(), app.getResults(), app.getResultsNames(), output)
    }

    private static void generateImports(PrintStream output) {
        List imports = [
                "from random import *",
                "from math import *",
                "",
                "# Data Analysis",
                "import pandas as pd",
                "import matplotlib.pyplot as plt",
                "",
                "# Machine Learning",
                "from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier, VotingClassifier, AdaBoostClassifier",
                "from sklearn.linear_model import LogisticRegression",
                "from sklearn.tree import DecisionTreeClassifier",
                "from sklearn.neighbors import KNeighborsClassifier",
                "from sklearn.neural_network import MLPClassifier",
                "from sklearn.model_selection import cross_val_score",
                "from sklearn.naive_bayes import GaussianNB, MultinomialNB",
                "from sklearn.svm import LinearSVC"
        ]
        output.println(imports.stream().reduce((a, b) -> a + "\n" + b).orElse(""))
    }

    private static void generateCodeLine(CodeLine line, PrintStream output) {
        try {
            GeneratorStrategy generator = line.getClass()
                    .getAnnotation(Generator.class).generator()
                    .getDeclaredConstructor().newInstance()
            List<String> sources = generator.toPython(line)
            output.println(sources.stream().reduce((a, b) -> a + "\n" + b).orElse(""))
        } catch (Exception e) {
            System.out.println("Exception during generation: " + e.getMessage())
            e.printStackTrace()
        }
    }

    private static void generateChart(Visualization visualization, List<String> results, List<String> resultsNames, PrintStream output) {
        output.println()
        List<String> sources = (new VisualizationGenerator(results: results, names: resultsNames)).toPython(visualization)
        output.println(sources.stream().reduce((a, b) -> a + "\n" + b).orElse(""))
    }
}
