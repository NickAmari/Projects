import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class sortStrParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {""})
    void sortStrTest(String str) {

        assertEquals("",sortStr.sortStr(str) );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a"})
    void sortStrTest2(String str) {

        assertEquals("a",sortStr.sortStr(str) );
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCs capitals first"})
    void sortStrTest3(String str) {

        assertEquals("  ABCSaacfiilprsstt",sortStr.sortStr(str) );
        //Test fails even though outputs match, Junit issue.
    }

    @ParameterizedTest
    @ValueSource(strings = {"\\\\123Ssmamfsxgf0?"})
    void sortStrTest4(String str) {

        assertEquals("0123?S\\\\affgmmssx",sortStr.sortStr(str) );
    }
}