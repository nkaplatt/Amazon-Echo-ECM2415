import javax.sound.sampled.AudioInputStream;


/*
* This class is for implementation for recording sound
* using two threads the aim is to have toggle listening
* while the other thread sends the conversation to the
* API for conversion.
*/

public class SoundThread implements Runnable {
  final String FILENAME; // file that belongs to the thread
  final AudioInputStream stream; // stream that belongs to thread
  final static String KEY1 = "4b90f6fd41164a1cb90085c9380ae42b"; // API key
  public static volatile boolean pause = true; // When the off button is pressed set this to true to pause threads
  public static Object lock = new Object();

  public static void main(String [] args) {

  }

  public void run() {
    /*
    * This block will see if the echo is in listening mode
    * and aim to carry out a recording, conversion to text,
    * analyse it for the wake word and notify the user if its found
    */
    boolean running = true;
      try {
          synchronized(lock){
            while (running) {
              if (pause){
                System.out.println(FILENAME + " is pausing");
                lock.wait();
              }
              if (!pause){
                System.out.println(FILENAME + " is waiting for a question");
                Echo.listen(stream, FILENAME); // Call the record function from echo
                String result = SpeechToText.run_conversion(KEY1, FILENAME); // runs the conversion for the wake word search

                if (result != null){ // If question is spoken
                  System.out.println("The question recorded is as follows:");
                  System.out.println(result); // print question
                } else {
                  System.out.println("No question spoken. Begin waiting for wake word.");
                  pause = true;
                  WakeWordThread.pause = false;
                  synchronized(WakeWordThread.lock){
                    WakeWordThread.lock.notify();
                  }
                }
              }
            }
            System.out.println(FILENAME + " thread stopped");
          }
      } catch (Exception e) {
        System.out.println(FILENAME + " has broken. Good job!");
      }
  }

  /*
  * Entry point to create a thread from the Echo class
  */
  public static void create_thread(String name, AudioInputStream stream_tmp) {
    (new Thread(new SoundThread(name, stream_tmp))).start(); // starts a new thread
  }

  /*
  * Constructor for a thread - has unique name (filepath of sound file)
  * and stream fr audio.
  */
  SoundThread(String name, AudioInputStream stream_tmp) {
    this.FILENAME = name; // The filename that this thread will record too and read from
    this.stream = stream_tmp; // threads unique stream - currently breaks with multiple threads.
  }
}
