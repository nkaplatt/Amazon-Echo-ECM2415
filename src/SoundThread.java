import javax.sound.sampled.AudioInputStream;


/*
* This class is for implementation of a wake word for recording sound
* using two threads the aim is to have one thread listening for the
* word that then passes to the 2nd thread to record and send speech
* to the API for conversion.
*/

public class SoundThread implements Runnable {
  final String FILENAME; // file that belongs to the thread
  final AudioInputStream stream; // stream that belongs to thread
  final static String KEY1 = "4b90f6fd41164a1cb90085c9380ae42b"; // API key
  public static volatile boolean stop = false; // When the off button is pressed set this to true to stop threads

  public static void main(String [] args) {

  }

  public void run() {
    boolean pause = false;

    /*
    * This block will see if the echo is in listening mode
    * and aim to carry out a recording, conversion to text,
    * analyse it for the wake word and notify the user if its found
    */
      try {
          while (!stop) {
              Echo.listen(stream, FILENAME); // Call the record function from echo
              String result = SpeechToText.run_conversion(KEY1, FILENAME); // runs the conversion for the wake word search

              if (result != null) { // If the result brings back something because keyword was heard
                System.out.println("Wake word heard - the question recorded is as follows.");
                System.out.println(result); // print question
              } else {
                System.out.println("Wake word \"Echo\" was not encountered so question not recorded");
              }
          }
          System.out.println("thread stopped");
      } catch (Exception e) {
        System.out.println("You managed to break it. Good job!");
      }
  }

  /*
  * Entry point to create a thread from the Echo class
  */
  public static void create_thread(String name) {
    (new Thread(new SoundThread(name))).start(); // starts a new thread
  }

  /*
  * Constructor for a thread - has unique name (filepath of sound file)
  * and stream fr audio.
  */
  SoundThread(String name) {
    this.FILENAME = name; // The filename that this thread will record too and read from
    AudioInputStream stream_tmp = RecordSound.setupStream();
    System.out.println("this stream " + stream_tmp);
    this.stream = stream_tmp; // threads unique stream - currently breaks with multiple threads.
  }

}
