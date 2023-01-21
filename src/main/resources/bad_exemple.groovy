read ("path.csv") >> test

validate(test, X_train, Y_train, cv: 5, scoring: ['acc': 'accuracy']) >> result_nb

