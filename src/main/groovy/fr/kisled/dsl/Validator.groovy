package fr.kisled.dsl

import fr.kisled.kernel.App
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.DataAcquisition

import java.util.function.Function

class Validator {
    private List<Function<List<CodeLine>, Boolean>> rules = [
            Validator::appStartsWithAcquisition,
            Validator::validationAfterAlgorithm,
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

    /**
     * Validation can use an algorithm that was not defined
     */
    private static boolean validationAfterAlgorithm(List<CodeLine> lines) {
        return true
    }

    /**
     * Variable defined before being used
     */
    private static boolean variableUsedAfterBeingDefined(List<CodeLine> lines) {
        return true
    }
}
