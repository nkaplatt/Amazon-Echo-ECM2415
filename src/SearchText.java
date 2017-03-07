import java.util.Arrays;

/**
 * Created by tilz on 03/03/2017.
 */

public class SearchText {
    static boolean findWakeWord (String text) {
       String WakeWord = "Microsoft";
       boolean found = Arrays.asList(text.split(" ")).contains(WakeWord);
       if(found) {
           System.out.println("wakeword found");

           //enter text after wakeword
           String [] parts = text.split(WakeWord);
           String question = parts[1];
           System.out.println(question);
       } else {
           //keep listening
       }
       return found;
    }

    public static void main (String[] argv) {
        // findWakeWord("");
    }

}
