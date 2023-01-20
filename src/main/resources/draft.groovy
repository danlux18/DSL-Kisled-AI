read ("path.csv") >> test
read ("path.csv") >> train

train[r(), r(0, 200)] >> X_train
train + 1 >> Y_train
Y_train - "column1" >> Y_train
X_train >> ['female': 1, 'male': 0] >> X_test

KNN(n_neighbors: btw(1, 11), algorithm: ['auto']) >> knn_algo
validate(knn_algo, X_train, Y_train, cv: 5, scoring: ['acc': 'accuracy']) >> result_knn

RandomForest(n_estimators: btw(299, 300), max_depth: 10) >> rf_algo
validate(rf_algo, X_train, Y_train, cv: 5, scoring: ['acc': 'accuracy']) >> result_nb

LogisticRegression(max_iter: btw(2, 5)) >> lr_algo

GaussianNB() >> gnb_algo

DecisionTreeClassifier() >> dtc_algo

GradientBoostingClassifier(n_estimators: 300) >> gbc_algo

LinearSVC(C: 0.0001) >> svc_algo

MLPClassifier(max_iter: 300) >> mlp_algo

disp X_test, X_train
chart "title", "xLabel", "yLabel"

