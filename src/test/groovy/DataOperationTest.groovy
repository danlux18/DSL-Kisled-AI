import fr.kisled.dsl.Parser
import fr.kisled.dsl.exception.UndeclaredVariableException
import fr.kisled.dsl.generator.Generator
import fr.kisled.dsl.generator.python.PyGenerator
import fr.kisled.kernel.App
import fr.kisled.kernel.Variable
import fr.kisled.kernel.dataops.Apply
import fr.kisled.kernel.dataops.ColumnDeletion
import fr.kisled.kernel.dataops.DataOperation
import fr.kisled.kernel.dataops.Mapping
import fr.kisled.kernel.dataops.Selection
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.nio.charset.StandardCharsets

import static org.junit.jupiter.api.Assertions.*

class DataOperationTest {
    Parser parser
    Generator generator

    App app

    @BeforeEach
    void setup() {
        parser = new Parser()
        generator = new PyGenerator()
    }

    String toolchain(String path) {
        File script = new File(path)
        app = parser.parse(script)

        final String utf8 = StandardCharsets.UTF_8.name()
        ByteArrayOutputStream buffer = new ByteArrayOutputStream()
        PrintStream out = new PrintStream(buffer, true, utf8)
        Generator generator = new PyGenerator()

        generator.generate(app, out)

        String content = buffer.toString()
        buffer.close()
        out.close()

        return content
    }

    @Test
    void dataOperationFailureUndeclaredVar() {
        assertThrows(UndeclaredVariableException.class, () ->
                toolchain("src/test/resources/data_operation_failure_var_undeclared.groovy"))
    }

    @Test
    void dataOperationParsed() {
        DataOperation select = new Selection()
        select.setOutput(new Variable("X_train"))
        select.setInput(new Variable("train"))
        select.setFilter('[:,"pixel0":"pixel783"]')

        DataOperation apply = new Apply()
        apply.setOutput(new Variable("X_test"))
        apply.setInput(new Variable("X_train"))
        apply.setOperation("lambda x : x/255")

        DataOperation map = new Mapping()
        map.setOutput(new Variable("Y_train"))
        map.setInput(new Variable("train"))
        map.setMapping("lambda x : x+1")

        DataOperation delete = new ColumnDeletion()
        delete.setOutput(new Variable("Y_train"))
        delete.setInput(new Variable("train"))
        delete.setColumn("column_name")

        toolchain("src/test/resources/data_operation_success.groovy")

        assertArrayEquals(
                new DataOperation[] {select, apply, map, delete},
                app.dataOperations.toArray(new DataOperation[0])
        )
    }

    @Test
    void dataOperationGenerated() {
        String select = "X_train = train.loc[:,\"pixel0\":\"pixel783\"]\n"
        String apply = "X_test = X_train.apply(lambda x : x/255)\n"
        String map = "Y_train = train.map(lambda x : x+1)\n"
        String delete = "Y_train = train.drop(\"column_name\")"

        String content = toolchain("src/test/resources/data_operation_success.groovy")

        println content
        println select + apply + map + delete

        assertTrue(content.contains(select + apply + map + delete))
    }
}
