read ("path.csv") >> test
read ("path.csv") >> train

train[r(0, 200), r()] >> X_train
train + 1 >> Y_train
Y_train - "column1" >> Y_train
X_train >> ['female': 1, 'male': 0] >> X_test

KNN(n_neighbors: btw(1, 11), algorithm: ['auto']) >> knn_algo

RandomForest(max_depth: [5], max_features: btw(1, 11), min_samples_split: btw(2, 11), min_samples_leaf: btw(1, 11), bootstrap: [true, false], criterion: ["gini", "entropy"]) >> rf_algo