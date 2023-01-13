package fr.kisled.dsl.generator

import fr.kisled.kernel.App
import fr.kisled.kernel.DataAcquisition

class PyGenerator extends Generator {
    @Override
    void generate(App app, PrintStream output) {
        generateImports(output)

        output.println()

        for (DataAcquisition data : app.getData()) {
            generateDataAcquisition(data, output)
        }

        output.println()
    }

    private static generateImports(PrintStream output) {
        output.println("# Data Analysis\n" +
                "import pandas as pd\n" +
                "import matplotlib.pyplot as plt\n" +
                "\n" +
                "# Machine Learning \n" +
                "from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier, VotingClassifier, AdaBoostClassifier\n" +
                "from sklearn.linear_model import LogisticRegression\n" +
                "from sklearn.tree import DecisionTreeClassifier\n" +
                "from sklearn.neighbors import KNeighborsClassifier\n" +
                "from sklearn.neural_network import MLPClassifier\n" +
                "from sklearn.model_selection import cross_val_score\n" +
                "from sklearn.naive_bayes import GaussianNB, MultinomialNB\n" +
                "from sklearn.svm import LinearSVC")
    }

    private static generateDataAcquisition(DataAcquisition data, PrintStream output) {
        output.printf("%s = pd.read_csv(\"%s\")\n", data.name, data.path)
    }
}
