import fr.kisled.dsl.Parser
import fr.kisled.dsl.generator.Generator
import fr.kisled.dsl.generator.PyGenerator
import fr.kisled.kernel.App
import fr.kisled.kernel.DataAcquisition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.nio.charset.StandardCharsets

import static org.junit.jupiter.api.Assertions.*

class DataAcquisitionTest {
    private App app = null

    @BeforeEach
    void parsing() {
        Parser parser = new Parser()
        File script = new File("src/test/resources/data_operation_success.groovy")
        app = parser.parse(script)

        assertNotNull(app)
    }

    @Test
    void dataAcquisitionParsed() {
        DataAcquisition X_test = new DataAcquisition()
        X_test.setName('X_test')
        X_test.setPath('../input/digit-recognizer/test.csv')
        DataAcquisition train = new DataAcquisition()
        train.setName('train')
        train.setPath('../input/digit-recognizer/train.csv')

        assertArrayEquals(
                new DataAcquisition[]{X_test, train},
                app.data.toArray(new DataAcquisition[0])
        )
    }

    @Test
    void dataAcquisitionGenerated() {
        String X_test = "X_test = pd.read_csv(\"../input/digit-recognizer/test.csv\")"
        String train = "train = pd.read_csv(\"../input/digit-recognizer/train.csv\")"

        final String utf8 = StandardCharsets.UTF_8.name()
        ByteArrayOutputStream buffer = new ByteArrayOutputStream()
        PrintStream out = new PrintStream(buffer, true, utf8)
        Generator generator = new PyGenerator()

        generator.generate(app, out)

        String content = buffer.toString()
        buffer.close()
        out.close()

        assertTrue(content.contains(X_test + "\n" + train))
    }
}
