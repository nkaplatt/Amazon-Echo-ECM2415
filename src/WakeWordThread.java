import javax.sound.sampled.AudioInputStream;


/*
* This class is for implementation for recording sound
* and waiting for the wake word to be spoken
*/

public class WakeWordThread implements Runnable {
  final String FILENAME; // file that belongs to the thread
  final AudioInputStream stream; // stream that belongs to thread
  final static String KEY1 = "42de859ace014243b12755d3287d102e"; // API key
  public static volatile boolean pause = true; // Checks if wakeword is spoken
  public static Object lock = new Object();
  private static int timer = 5;

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
                lock.wait();
                ButtonNoise.readyForWakeWord();
              }
              Echo.listen(stream, FILENAME, timer); // Call the record function from echo
              String result = SpeechToText.run_conversion(KEY1, FILENAME); // runs the conversion for the wake word search
              if (result != null) { // If the result brings back something because keyword was heard
                ButtonNoise.heardEcho(); // heard the wake word
                pause = true; // pause threads that listen for wake word as moving to question thread
                SoundThread.pause = false;
                synchronized(SoundThread.lock){
                  SoundThread.lock.notifyAll();
                }
              } else {
                  System.out.println("Waiting for the wake word \"Echo\"");
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
