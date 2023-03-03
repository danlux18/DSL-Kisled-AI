/**
 * This exemple is taken from "Comparing ML Models for Classification"
 * - https://www.kaggle.com/code/adoumtaiga/comparing-ml-models-for-classification
 */
read("./data/train.csv", 'PassengerId') >> train

//train.apply { train -> (train.isnull().sum().sort_values(ascending=False) * 100)/train.shape[0] } >> train
train - ['Cabin', 'Ticket', 'Name'] >> train

train.apply { x -> x.fillna(x.value_counts().index[0]) } >> train

train['Sex'] >> ['female': 1, 'male': 0] >> train['Sex']
train['Embarked'] >> ['Q': 0, 'C': 1, 'S': 2] >> train['Embarked']

disp train.head()

train - 'Survived' >> X
train['Survived'] >> y

// Logistic Regression
LogisticRegression(max_iter: 500) >> lr
validate(lr, X, y, cv: 8) >> scores_lr
disp scores_lr

// Naive Bayes
GaussianNB() >> nb
validate(nb, X, y, cv: 8) >> scores_nb
disp scores_nb

// KNN
KNN(n_neighbors: 5, algorithm: "auto") >> knn
validate(knn, X, y, cv: 8) >> scores_knn
disp scores_knn

// Random Forest
RandomForest(n_estimators: 300, max_depth: 10) >> rand
validate(rand, X, y, cv: 6) >> scores_rand
disp scores_rand

// Decision Tree
DecisionTreeClassifier() >> tr
validate(tr, X, y, cv: 6) >> scores_tr
disp scores_tr

// Gradient Boosting
GradientBoostingClassifier(n_estimators: 300) >> grd
validate(grd, X, y, cv: 8) >> scores_grd
disp scores_grd

// Support Vector Machine - SVM
LinearSVC(C: 0.0001) >> sv
validate(sv, X, y, cv: 8) >> scores_sv
disp scores_sv

// Neural Netword
MLPClassifier(max_iter: 300) >> mlp
validate(mlp, X, y, cv: 8) >> scores_mlp
disp scores_mlp

chart "Result", "Accuracy", "Models", "barh", data.mean()