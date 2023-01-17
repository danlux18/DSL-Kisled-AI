read ("path.csv") >> test
read ("path.csv") >> train

train[r(0, 200), r()] >> X_train
train + 1 >> Y_train
train - 1 >> Y_train
train * 5 >> Y_train
train / 5 >> Y_train