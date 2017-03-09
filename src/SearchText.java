import java.util.Arrays;

/**
 * Created by tilz on 03/03/2017.
 */

public class SearchText {
    static String findWakeWord (String text) {
       String WakeWord = "echo";
       String question = null;
       boolean found = Arrays.asList(text.split(" ")).contains(WakeWord);
       if (found) {
           System.out.println("wakeword found");

           //enter text after wakeword
           String [] parts = text.split(WakeWord);
           question = parts[1];
       } else {
           //keep listening
       }
       return question;
    }

    public static void main (String[] argv) {
        // findWakeWord("");
    }

}
