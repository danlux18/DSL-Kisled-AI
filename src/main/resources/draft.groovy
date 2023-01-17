read ("path.csv") >> test
read ("path.csv") >> train

train[r(0, 200), r()] >> X_train