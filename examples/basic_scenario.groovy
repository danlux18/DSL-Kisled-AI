/**
 * This exemple is taken from "Comparing ML Models for Classification"
 * - https://www.kaggle.com/code/adoumtaiga/comparing-ml-models-for-classification
 */
read("./data/train.csv", 'PassengerId') >> train

//train.apply { train -> (train.isnull().sum().sort_values(ascending=False) * 100)/train.shape[0] } >> train

train - ['Cabin', 'Ticket', 'Name'] >> train