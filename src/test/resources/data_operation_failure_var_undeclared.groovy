data "X_train" from "train" select '[:,"pixel0":"pixel783"]'
data "X_test" from "X_train" apply "lambda x : x/255"
