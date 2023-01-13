package fr.kisled.dsl.generator;

import fr.kisled.kernel.App;
import fr.kisled.kernel.DataAcquisition;
import fr.kisled.kernel.Variable;
import fr.kisled.kernel.dataops.*;

import java.io.PrintStream;

public class PyGenerator extends Generator {
    @Override
    public void generate(App app, PrintStream output) {
        generateImports(output);

        output.println();

        for (DataAcquisition data : app.getData()) {
            generateDataAcquisition(data, output);
        }

        output.println();

        for (DataOperation dataOperation : app.getDataOperations()) {
            generateDataOperation(dataOperation, output);
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

    private void generateDataAcquisition(DataAcquisition data, PrintStream output) {
        output.printf("%s = pd.read_csv(\"%s\")\n", data.getName(), data.getPath());
    }

    private void generateDataOperation(DataOperation dataOperation, PrintStream output) {
        output.printf("%s = ", dataOperation.getOutput().getName());
        if (dataOperation instanceof Apply)
            output.printf("%s.apply(%s)\n", dataOperation.getInput().getName(), ((Apply) dataOperation).getOperation());
        else if (dataOperation instanceof ColumnDeletion)
            output.printf("%s.drop(%s)\n", dataOperation.getInput().getName(), ((ColumnDeletion) dataOperation).getColumn());
        else if (dataOperation instanceof Mapping)
            output.printf("%s.map(%s)\n", dataOperation.getInput().getName(), ((Mapping) dataOperation).getMapping());
        else if (dataOperation instanceof Selection)
            output.printf("%s.loc%s\n", dataOperation.getInput().getName(), ((Selection) dataOperation).getFilter());
    }
}
