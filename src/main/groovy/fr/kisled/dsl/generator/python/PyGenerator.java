package fr.kisled.dsl.generator.python;

import fr.kisled.dsl.generator.Generator;
import fr.kisled.kernel.App;
import fr.kisled.kernel.CodeLine;
import fr.kisled.kernel.DataAcquisition;
import fr.kisled.kernel.ops.ApplyOp;
import fr.kisled.kernel.ops.DropColumnOp;
import fr.kisled.kernel.ops.SelectOp;

import java.io.PrintStream;

public class PyGenerator extends Generator {
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
        if (line instanceof DataAcquisition var) {
            output.printf("%s = pd.read_csv(\"%s\")\n", var.getVarname(), var.getPath());
        }
        else if (line instanceof SelectOp op) {
            output.printf("%s = %s%s\n", op.getOutput_varname(), op.getInput_varname(), op.getRange());
        }
        else if (line instanceof ApplyOp op) {
            output.printf("%s = %s.apply(%s)\n", op.getOutput_varname(), op.getInput_varname(), op.getLambda());
        }
        else if (line instanceof DropColumnOp op) {
            output.printf("%s = %s.drop(%s)\n", op.getOutput_varname(), op.getInput_varname(), op.getDropped_column());
        }
    }
}
