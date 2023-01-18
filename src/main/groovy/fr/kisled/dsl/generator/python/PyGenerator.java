package fr.kisled.dsl.generator.python;

import fr.kisled.dsl.generator.algorithm.Generator;
import fr.kisled.dsl.generator.algorithm.GeneratorStrategy;
import fr.kisled.kernel.App;
import fr.kisled.kernel.CodeLine;

import java.io.PrintStream;
import java.util.List;

public class PyGenerator extends fr.kisled.dsl.generator.Generator {
    @Override
    public void generate(App app, PrintStream output) {
        output.println("\"\"\" App " + app.getName() + " \"\"\"");

        generateImports(output);

        output.println();

        for (CodeLine line : app.getCodeLines()) {
            generateCodeLine(line, output);
        }
    }

    private void generateImports(PrintStream output) {
        output.println("""
                # Data Analysis
                import pandas as pd
                import matplotlib.pyplot as plt

                # Machine Learning
                from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier, VotingClassifier, AdaBoostClassifier
                from sklearn.linear_model import LogisticRegression
                from sklearn.tree import DecisionTreeClassifier
                from sklearn.neighbors import KNeighborsClassifier
                from sklearn.neural_network import MLPClassifier
                from sklearn.model_selection import cross_val_score
                from sklearn.naive_bayes import GaussianNB, MultinomialNB
                from sklearn.svm import LinearSVC""");
    }

    private void generateCodeLine(CodeLine line, PrintStream output) {
        try {
            GeneratorStrategy generator = line.getClass().getAnnotation(Generator.class).generator().getDeclaredConstructor().newInstance();
            List<String> sources = generator.toPython(line);
            output.println(String.join("\n", sources.toArray(new String[0])));
        } catch (Exception e) {
            System.out.println("Exception during generation: " + e.getMessage());
        }
    }
}
