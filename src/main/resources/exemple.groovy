// ==== Exemple ====
/**
 * ## Data Acquisition
 */
read ('path.csv') >> test
read ('path.csv') >> train

/**
 * ## Pre-Processing
 */
train[r(0, 200), r()] >> X_train
train + 1 >> Y_train
Y_train - "column_name" >> Y_train
X_train >> ['female': 1, 'male': 0] >> X_test
X_test / 255 >> X_test

/**
 * ## Hyperparameter
 */
rand_list_nb = ["clf_nb__var_smoothing": logspace(-9, 0, 5)]

/**
 * ## Common variables
 */
// Define parameters ex.: pca
pca = PCA(n_components: 0.95)
kfold = StratifiedKFold(n_splits: 2, shuffle: true)

scoring = ['acc': 'accuracy']

/**
 * ## KNN Algorithm
 */
// Algo 1
// For n_neighbors btw 0 n 50
KNN (n_neighbors: btw(1, 11), algorithm: ['auto']) >> knn
validate(knn, X_train, Y_train, cv: 5, scoring: ['acc': 'accuracy']) >> result_knn // validate stands for cross_validate method
disp result_knn
disp result_knn.mean(), result_knn.std()

/**
 * Naive Bayes Algorithm
 */
// Algo 2
//nb_pipe = ['pca': pca] | ['clf_nb': GaussianNB()]
//algo = RandomizedSearchCV(estimator: nb_pipe, param_distribution: rand_list_nb, cv: kfold, verbose: 2, n_jobs: -1, n_iter: 5)
//result_nb = validate(algo, X_train, Y_train, cv: 5, scoring: scoring)
//chart "stick", result_nb

/**
 * Random Forest Algorithm
 */
// Algo 3
RandomForest(n_estimators: btw(299, 300), max_depth: 10) >> rf_algo
validate(rf_algo, X_train, Y_train, scoring: scoring) >> result_rf
disp result_rf.mean(), result_rf.std()

//DSL value : Comparison with visualization
chart "title", "xLabel", "yLabel"
