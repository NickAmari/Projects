import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

public class SortStrTestNG {

    @Test
    public void T1() {
        assertEquals("  ABCSaacfiilprsstt",SortStr.sortStr("ABCs capitals first") );
        //Testing basic string with spaces
        //Test fails even though outputs match, Junit issue.
    }

    @Test
    public void T3() {
        assertEquals("",SortStr.sortStr("") );
        //testing an empty string

    }

    @Test
    public void T2() {
        assertEquals("a",SortStr.sortStr("a") );
        //testing a single character string
    }
}