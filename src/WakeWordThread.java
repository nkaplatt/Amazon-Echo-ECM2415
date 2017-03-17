import javax.sound.sampled.AudioInputStream;


/*
* This class is for implementation for recording sound
* and waiting for the wake word to be spoken
*/

public class WakeWordThread implements Runnable {
  final String FILENAME; // file that belongs to the thread
  final AudioInputStream stream; // stream that belongs to thread
  final static String KEY1 = "4b90f6fd41164a1cb90085c9380ae42b"; // API key
  public static boolean pause = true; // Checks if wakeword is spoken
  public static Object lock = new Object();

  public static void main(String [] args) {

  }

  public void run() {
    /*
    * Check for wake word
    */
    boolean running = true;
      try {
        synchronized(lock){
            while (running) {
              if (pause){
                System.out.println(FILENAME + " is pausing");
                lock.wait();
                System.out.println(FILENAME + " is unpausing");
              }
              Echo.listen(stream, FILENAME); // Call the record function from echo
              String result = SpeechToText.run_conversion(KEY1, FILENAME); // runs the conversion for the wake word search

              if (result != null) { // If the result brings back something because keyword was heard
                System.out.println("Wake word heard");
                pause = true;
                SoundThread.pause = false;
                synchronized(SoundThread.lock){
                  SoundThread.lock.notifyAll();
                }
              } else {
                System.out.println("Waiting for the word \"Echo\"");
              }
            }
          }
          System.out.println(FILENAME + " thread stopped");
      } catch (Exception e) {
        System.out.println(FILENAME + " has broken. Good job!");
      }
    }


  /*
  * Entry point to create a thread from the Echo class
  */
  public static void create_wakeword_thread(String name, AudioInputStream stream_tmp) {
    (new Thread(new WakeWordThread(name, stream_tmp))).start(); // starts a new thread
  }

  /*
  * Constructor for a thread - has unique name (filepath of sound file)
  * and stream fr audio.
  */
  WakeWordThread(String name, AudioInputStream stream_tmp) {
    this.FILENAME = name; // The filename that this thread will record too and read from
    this.stream = stream_tmp; // threads unique stream - currently breaks with multiple threads.
  }

}
