package fr.kisled.dsl

import fr.kisled.kernel.App
import fr.kisled.kernel.CodeLine
import fr.kisled.kernel.DataAcquisition
import fr.kisled.kernel.Validation
import fr.kisled.kernel.algorithm.Algorithm
import fr.kisled.kernel.ops.Op
import fr.kisled.kernel.ops.SelectOp
import fr.kisled.kernel.utils.Range
import fr.kisled.kernel.visualization.Printer
import fr.kisled.kernel.visualization.Visualization

import java.util.function.Function

class Validator {
    private List<Function<List<CodeLine>, Boolean>> rules = [
            Validator::appStartsWithAcquisition,
            Validator::variableUsedAfterBeingDefined,
            Validator::checkChartType
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

    private static boolean checkIfVariableExist(Map<String, String> variables, String variable, String expectedType, int lineno) {
        if (!variables.containsKey(variable)) {
            System.err.printf("Variable \"%s\" undefined (line %d)\n", variable, lineno)
            return false
        }

        if (expectedType != null && expectedType != variables.get(variable)) {
            System.err.printf(
                    "Variable \"%s\" is of type %s but type %s was expected (line %d)\n",
                    variable,
                    variables.get(variable),
                    expectedType,
                    lineno)
            return false
        }

        return true
    }

    private static boolean checkRange(Range range) {
        if (range.start == null || range.stop == null)
            return true

        if (range.step > 0 && range.start <= range.stop)
            return true

        if (range.step < 0 && range.start >= range.stop)
            return true

        System.err.printf(
                "Range (start = %d, stop = %d, step = %d) badly formatted\n",
                range.start,
                range.stop,
                range.step
        )
        return false
    }

    private static boolean checkSelectionCriteria(SelectOp op) {
        boolean pass = true

        if (op.range instanceof Range)
            pass &= checkRange(op.range as Range)
        else if (op.range instanceof List)
            for (Object criteria : op.range)
                if (criteria instanceof Range)
                    pass &= checkRange(criteria)

        return pass
    }

    /**
     * Variable defined before being used
     */
    private static boolean variableUsedAfterBeingDefined(List<CodeLine> lines) {
        Map<String, String> variables = [:]
        boolean pass = true
        int lineno = 1
        for (CodeLine line : lines) {
            if (line instanceof DataAcquisition) {
                variables.put(line.varname, "Dataframe")
            }
            else if (line instanceof Op) {
                pass &= checkIfVariableExist(variables, line.input_varname, "Dataframe", lineno)

                if (line instanceof SelectOp)
                    pass &= checkSelectionCriteria(line)

                variables.put(line.output_varname, "Dataframe")
            }
            else if (line instanceof Algorithm) {
                variables.put(line.output_varname, "Algorithm")
            }
            else if (line instanceof Validation) {
                pass &= checkIfVariableExist(variables, line.algo, "Algorithm", lineno)
                pass &= checkIfVariableExist(variables, line.x, "Dataframe", lineno)
                pass &= checkIfVariableExist(variables, line.y, "Dataframe", lineno)
                variables.put(line.varname, "Result")
            }
            else if (line instanceof Printer) {
                for (String varname : line.vars.keySet()) {
                    pass &= checkIfVariableExist(variables, varname, null, lineno)
                }
            }
            lineno++
        }

        return pass
    }

    private static List chartTypes = ['plot', 'bar', 'barh', 'scatter', 'hist']
    private static boolean checkChartType(List<CodeLine> lines) {
        String type = ""
        boolean pass = lines.findAll { it instanceof Visualization }
                .stream().allMatch(v -> chartTypes.contains(type = v.properties['type']))

        if (!pass) {
            System.err.printf(
                    "Chart type should be %s but was %s\n",
                    chartTypes.stream().reduce((a, b) -> a + ', ' + b).orElse(""),
                    type
            )
        }

        return pass
    }
}
