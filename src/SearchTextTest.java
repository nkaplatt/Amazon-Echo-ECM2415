import junit.framework.TestCase;

/**
 * Created on 06/03/2017.
 */
public class SearchTextTest extends TestCase {
    public String textTrue;
    public String textFalse;
    public void setUp() throws Exception {
        super.setUp();
        textTrue = "echo what is the melting point of silver";
        textFalse = "Hi Andrew how are you?";

    }

    public void tearDown() throws Exception {
        textTrue = null;
        textFalse = null;
    }

    public void testFindWakeWord() throws Exception {
        assertEquals("Echo", SearchText.findWakeWord(textTrue));
        assertEquals( null, SearchText.findWakeWord(textFalse));
        tearDown();
    }


}