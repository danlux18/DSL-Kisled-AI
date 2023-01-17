package fr.kisled.dsl.generator.jupyter

import fr.kisled.dsl.generator.Generator
import fr.kisled.kernel.App
import groovy.json.JsonOutput

class JupyterGenerator extends Generator {
    @Override
    void generate(App app, PrintStream output) {
        def cells = [generateMarkdownCell(["# App ${app.name}"]), generateImportCell()]

        def jupyter = [
                cells: cells,
                metadata: [
                        kernelspec: [
                                display_name: "Python 3 (ipykernel)",
                                language: "python",
                                name: "python3"
                        ],
                        language_info: [
                                codemirror_mode: [
                                        name: "ipython",
                                        version: 3
                                ],
                                file_extension: ".py",
                                mimetype: "text/x-python",
                                name: "python",
                                nbconvert_exporter: "python",
                                pygments_lexer: "ipython3",
                                version: "3.11.1"
                        ]
                ],
                nbformat: 4,
                nbformat_minor: 4
        ]

        output.print JsonOutput.toJson(jupyter)
    }

    private static def generateCodeCell(List<String> sources) {
        return [cell_type: "code", execution_count: null, metadata: [:], outputs: [], source: sources]
    }
    private static def generateMarkdownCell(List<String> sources) {
        return [cell_type: "markdown", metadata: [:], source: sources]
    }

    private static def generateImportCell() {
        def sources = [
                "# Data Analysis\n",
                "import pandas as pd\n",
                "import matplotlib.pyplot as plt\n",
                "\n",
                "# Machine Learning\n",
                "from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier, VotingClassifier, AdaBoostClassifier\n",
                "from sklearn.linear_model import LogisticRegression\n",
                "from sklearn.tree import DecisionTreeClassifier\n",
                "from sklearn.neighbors import KNeighborsClassifier\n",
                "from sklearn.neural_network import MLPClassifier\n",
                "from sklearn.model_selection import cross_val_score\n",
                "from sklearn.naive_bayes import GaussianNB, MultinomialNB\n",
                "from sklearn.svm import LinearSVC"
        ]

        return generateCodeCell(sources)
    }
}
