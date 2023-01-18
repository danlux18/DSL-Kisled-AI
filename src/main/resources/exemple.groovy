// ==== Exemple ====
/**
 * ## Data Acquisition
 */
test = read 'path.csv'
train = read 'path.csv'

/**
 * ## Pre-Processing
 */
X_train = train[r(0, 200), r()]
Y_train = 'Y_train'
Y_train = train + 1
Y_train = Y_train - "column_name"
X_test = ['female': 1, 'male': 0] <- X_train
X_test = X_test / 255

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
algo = KNN n_neighbors: btw(1, 11), algorithm: ['auto']
result_knn = validate(algo, X_train, Y_train, cv: 5, scoring: scoring) // validate stands for cross_validate method
disp result_knn
disp result_knn.mean(), result_knn.std()

/**
 * Naive Bayes Algorithm
 */
// Algo 2
nb_pipe = ['pca': pca] | ['clf_nb': GaussianNB()]
algo = RandomizedSearchCV(estimator: nb_pipe, param_distribution: rand_list_nb, cv: kfold, verbose: 2, n_jobs: -1, n_iter: 5)
result_nb = validate(algo, X_train, Y_train, cv: 5, scoring: scoring)
chart "stick", result_nb

/**
 * Random Forest Algorithm
 */
// Algo 3
algo = RandomForest clf__max_depth: [5, null], clf__max_features: sp_randint(1, 11), clf__min_samples_split: sp_randint(2, 11), clf__min_samples_leaf: sp_randint(1, 11), clf__bootstrap: [true, false], clf__criterion: ["gini", "entropy"]
result_rf = validate(algo, X_train, Y_train, scoring: scoring)
disp result_rf.mean(), result_rf.std()

//DSL value : Comparison with visualization
// Disp. result_knn , result_nb, result_rf
result f('accuracy', gt(20), lt(30)), f('avg_time', w(50))
