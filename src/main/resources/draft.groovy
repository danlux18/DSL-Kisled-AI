read ("path.csv") >> test
read ("path.csv") >> train

train[r(0, 200), r()] >> X_train
train["toto"] >> Y_train
train[0] >> Y_train
train[r()] >> Y_train