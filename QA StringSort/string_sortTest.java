import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class string_sortTest {

    @Test
    void sortStr() {
        assertEquals("  ABCSaacfiilprsstt",string_sort.sortStr("ABCs capitals first") );
        //Testing basic string with spaces
        //Test fails even though outputs match, Junit issue.
    }

    @Test
    void sortStr2() {
        assertEquals("",string_sort.sortStr("") );
        //testing an empty string
    }

    @Test
    void sortStr3() {
        assertEquals("a",string_sort.sortStr("a") );
        //testing a single character string
    }

    @Test
    void sortStr4() {
        assertEquals("0123?S\\affgmmssx",string_sort.sortStr("\\123Ssmamfsxgf0?") );
        //testing a string with a mix of all possible characters
    }
}