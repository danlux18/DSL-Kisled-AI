package fr.kisled.dsl.builders

import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.Validation

class ValidationBuilder extends CodeBuilder {
    String algo
    String X_train
    String Y_train
    def kwargs

    String varname

    ValidationBuilder(VariableBuilder algo, VariableBuilder X_train, VariableBuilder Y_train, kwargs) {
        this.algo = algo.getName()
        this.X_train = X_train.getName()
        this.Y_train = Y_train.getName()
        this.kwargs = kwargs
    }

    def rightShift(VariableBuilder v) {
        varname = v.getName()
    }

    @Override
    CodeLine build() {
        return new Validation(
                algo: algo,
                x: X_train,
                y: Y_train,
                kwargs: kwargs,
                varname: varname
        )
    }
}
