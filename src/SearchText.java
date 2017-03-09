import java.util.Arrays;

/**
 * Created by tilz on 03/03/2017.
 *
 * Searches text for wakeword
 */


public class SearchText{
    /**
     * This is a method which searches the string of text and returns a
     * boolean if the wakeword is found and splits the string use the
     * wakeword as the splitter, anything after wakeword is interpreted
     * as the question.
     *
     * @param text  text is generated by the sound recording transformed into text
     * @return found  returns a boolean to say weather the wakeword was found or not
     */
    static string findWakeWord (String text ){
        String WakeWord = "Echo";
        String question = null;
        boolean found = Arrays.asList(text.split(" ")).contains(WakeWord);
        if(found) {

           //enter text after wakeword
           String [] parts = text.split(WakeWord);
           question = parts[1];
       } else {
           //keep listening
       }
       return question;
    }


    public static void main (String[] argv){
       // test findWakeWord("Alexa what is the melting point of silver");

    }

}