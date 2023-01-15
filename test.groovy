//ACQUISITION
// acquire VAR_NAME with CSV_PATH
data "sample_submission" with "../input/digit-recognizer/sample_submission.csv"
data "X_test" with "../input/digit-recognizer/test.csv"
data "train" with "../input/digit-recognizer/train.csv"

data "X_train" from "train" select '[:,"pixel0":"pixel783"]'
data "Y_train" from "train" select '[:, "label"]'
data "X_test" from "X_train" apply "lambda x : x/255"
data "Y_train" from "train" map "lambda x : x+1"
data "Y_train" from "train" delete "colum_name"
