package fr.kisled.dsl.builders.transformations

import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.BinaryExpression
import org.codehaus.groovy.ast.expr.ClosureExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.classgen.GeneratorContext
import org.codehaus.groovy.control.CompilationFailedException
import org.codehaus.groovy.control.CompilationUnit
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.control.customizers.CompilationCustomizer
import org.codehaus.groovy.ast.ModuleNode
import org.codehaus.groovy.ast.expr.Expression

class ClosureCustomizer extends CompilationCustomizer {
    public static List<String> LAMBDAS = []

    ClosureCustomizer() {
        super(CompilePhase.CONVERSION)
    }

    @Override
    void call(SourceUnit sourceUnit, GeneratorContext generatorContext, ClassNode classNode) throws CompilationFailedException {
        ModuleNode ast = sourceUnit.getAST()
        List<ExpressionStatement> statements = ast.properties.get('statementBlock').properties.get('statements')

        statements.each {
            stmt -> visitStatement(stmt)
        }
    }

    private static void visitStatement(ExpressionStatement stmt) {
        Expression e = stmt.expression
        if (e instanceof BinaryExpression) {
            visitExpression(e.leftExpression)
        }
    }

    private static void visitExpression(Expression e) {
        if (e instanceof MethodCallExpression) {
            if (e.method.value == "apply")
                visitArguments(e.arguments)
        }
    }

    private static void visitArguments(Expression arg) {
        if (arg instanceof ArgumentListExpression)
            visitArgumentExpressions(arg.expressions)
    }

    private static void visitArgumentExpressions(List<Expression> expressions) {
        expressions.each {
            expr -> {
                if (expr instanceof ClosureExpression) {
                    LAMBDAS.add(closure2string(expr))
                }
            }
        }
    }

    private static String closure2string(ClosureExpression expr) {
        String params = expr.parameters.collect { it.name }
                .stream()
                .reduce((a, b) -> a + ", " + b).orElse("")
        String code = expr.code.text
                .replace("{ ", "")
                .replace(" }", "")
                .replace("this.", "")
        return "lambda $params: $code"
    }

    @Override
    void doPhaseOperation(CompilationUnit unit) throws CompilationFailedException {
        super.doPhaseOperation(unit)
    }

    @Override
    boolean needSortedInput() {
        return super.needSortedInput()
    }
}
