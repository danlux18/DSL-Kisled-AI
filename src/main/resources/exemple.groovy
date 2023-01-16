//ACQUISITION
// acquire VAR_NAME with CSV_PATH
data "test" with "path.csv"
data "train" with "path.csv"


//OPERATIONS
//data VAR_NAME from VAR_NAME select FILTER (FILTER ==> "[debut_x:fin_x,debut_y:fin_y]" ou "[label]")
data "X_train" from "train" select "[0:200,:]"
data "Y_train" from "train" map "lambda x : x+1"
data "Y_train" from "train" delete "colum_name"
data "X_test" from "X_train" apply "lambda x : x/255"

//HYPER PARAMETERS
//
hparam "test_int" integer 5
hparam "clf_knn__n_neighbors" integer() initializer "sp_randint(1, 11)"
hparam "test_string_value" string "value"
hparam "clf_knn__algorithm" array "auto" "immagineallthepeople"

//ALGORITHMS
//algorithm "knn" input "input" output "output" hparams "clf_knn__n_neighbors" "clf_knn__algorithm"

rand_search_cv "gs_KNN" estimator "pipe_knn" param_distributions "rand_list_KNN" cv " StratifiedKFold(n_splits=2, shuffle = True)" verbose 2 n_jobs -1 n_iter 5

//VISUALIZATION
visualization array input "var_name"
visualization chart "stickdiagram" input "var_name"