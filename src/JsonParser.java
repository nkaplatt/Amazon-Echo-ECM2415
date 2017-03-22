import java.util.Arrays;

/**
 * Created by Gareth on 21/03/2017.
 */

public class JsonParser {

    public String jsonParser(String jsonstr) {

        String answer="";
        return answer;
    }

/*
    {"version":"3.0","header":{"status":"success","scenario":"smd","name":"Hey what are you print this time?","lexical":"hey what are you print this time","properties":{"requestid":"28435095-4795-4737-93cf-f1bc75f90f38","HIGHCONF":"1"}},"results":[{"scenario":"smd","name":"Hey what are you print this time?","lexical":"hey what are you print this time","confidence":"0.6426966","properties":{"HIGHCONF":"1"}}]}
*/
    /**
    Checks to see if the return from mirosoftcog was successful

    **/
    static Boolean findStatus (String text ){
        String success = "\"status\":\"success\"";
        if (text.contains(success)) {
            return true;
        } else {
            return false;
        }
    }


    static String findQuestion (String text ){
        // Split path into segments
        //"name":"Hey what are you print this time?"
        String segments[] = text.split(",");
        String tempstr = "";
        String returnstr = "";
        for (int x = 0; x < segments.length; x++) {
            tempstr = segments[x];
            if (tempstr.contains("\"name\":")){
                returnstr = tempstr;
                returnstr = returnstr.replace("\"name\":\"", "");
                returnstr = returnstr.replace("\"", "");
                return returnstr;
            }
        }
        return "Failed";
    }

    static String start(String check){
        if (findStatus(check)){
            return (findQuestion(check));
        } else {
            return null;
        }
    }

    // Need to write same thing as above but for Wolfram API.
    static Boolean findStatusAnswer(String text){
        String success = "\"success\" : true";
        if (text.contains(success)) {
            return true;
        } else {
            return false;
        }
    }


    static String findAnswer(String text ) {
        // Split path into segments
        //"name":"Hey what are you print this time?"
        String segments[] = text.split("\n");
        String tempstr = "";
        String targetSeg = "";
        Boolean foundAnswer = false;

        for (int x = 0; x < segments.length; x++) {
            tempstr = segments[x];

            if (tempstr.contains("\"id\" : \"Result\"")) {
              foundAnswer = true;
            }
            if (tempstr.contains("\"plaintext\"") && foundAnswer == true) {
              targetSeg = tempstr;
              targetSeg = targetSeg.replace("\"plaintext\" : \"", "");
              targetSeg = targetSeg.replace("\"", "");
              if (targetSeg == null) {
                return "Hmmmmmm, I dont think I know the answer to that";
              }
              return targetSeg;
            }
        }
        return "Hmmmmmm, I dont think I know the answer to that";
    }

    static String stripAnswer(String check){
        if (findStatusAnswer(check)){
            return (findAnswer(check));
        } else {
            return "Hmmmmmm, I dont think I know the answer to that";
        }
    }

    public static void main (String[] argv) {

    }
}
