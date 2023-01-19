package fr.kisled.dsl

import fr.kisled.kernel.App
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.DataAcquisition
import fr.kisled.kernel.Validation
import fr.kisled.kernel.algorithm.Algorithm
import fr.kisled.kernel.ops.Op

import java.util.function.Function

class Validator {
    private List<Function<List<CodeLine>, Boolean>> rules = [
            Validator::appStartsWithAcquisition,
            Validator::variableUsedAfterBeingDefined
    ]

    /**
     * Assert that an application is correctly formatted
     * @param app the application
     * @return true if the application pass all rules, false otherwise
     */
    boolean validate(App app) {
        boolean pass = true

        for (Function<List<CodeLine>, Boolean> rule : rules) {
            pass &= rule.apply(app.getCodeLines())
        }

        return pass
    }

    /**
     * The application must start with a data acquisition
     */
    private static boolean appStartsWithAcquisition(List<CodeLine> lines) {
        boolean pass = !lines.isEmpty() && (lines.get(0) instanceof DataAcquisition)

        if (!pass)
            System.err.printf("App should start with data acquisition. Got: %s\n",
                    lines.isEmpty() ? "empty application" : lines.get(0).class.getSimpleName())

        return pass
    }

    private static boolean checkIfVariableExist(List<String> variables, String variable, int lineno) {
        if (!variables.contains(variable)) {
            System.err.printf("Variable \"%s\" undefined (line %d)\n", variable, lineno)
            return false
        }
        return true
    }

    /**
     * Variable defined before being used
     */
    private static boolean variableUsedAfterBeingDefined(List<CodeLine> lines) {
        List<String> variables = []
        boolean pass = true
        int lineno = 1
        for (CodeLine line : lines) {
            if (line instanceof DataAcquisition) {
                variables.add(line.varname)
            }
            else if (line instanceof Op) {
                pass &= checkIfVariableExist(variables, line.input_varname, lineno)
                variables.add(line.output_varname)
            }
            else if (line instanceof Algorithm) {
                variables.add(line.output_varname)
            }
            else if (line instanceof Validation) {
                pass &= checkIfVariableExist(variables, line.algo, lineno)
                pass &= checkIfVariableExist(variables, line.x, lineno)
                pass &= checkIfVariableExist(variables, line.y, lineno)
                variables.add(line.varname)
            }
            lineno++
        }

        return pass
    }
}
