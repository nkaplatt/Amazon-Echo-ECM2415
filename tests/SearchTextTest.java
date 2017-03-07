import junit.framework.TestCase;

/**
 * Created by tilz on 06/03/2017.
 */
public class SearchTextTest extends TestCase {
    public String textTrue;
    public String textFalse;
    public void setUp() throws Exception {
        super.setUp();
        textTrue = "Alexa what is the time?";
        textFalse = "Hi tilly how are you?";
    }

    public void tearDown() throws Exception {
        textTrue = null;
        textFalse = null;
    }

    public void testFindWakeWord() throws Exception {
        assertEquals(true, SearchText.findWakeWord(textTrue));
        assertEquals(false, SearchText.findWakeWord(textFalse));
        tearDown();
    }


}
