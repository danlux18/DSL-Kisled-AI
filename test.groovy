//ACQUISITION
// acquire VAR_NAME with CSV_PATH
data "sample_submission" with "../input/digit-recognizer/sample_submission.csv"
data "X_test" with "../input/digit-recognizer/test.csv"
data "train" with "../input/digit-recognizer/train.csv"
