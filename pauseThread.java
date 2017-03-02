public class pauseThread implements Runnable{

  public static void main(String [] args)
  {
    (new Thread(new pauseThread())).start();
  }

  public void run(){
    boolean pause = false;
    String temp = "Pause echo";                          //JUST FOR TESTING
      try{
          while (!pause){
              System.out.println("Hello user");          //JUST FOR TESTING
              Thread.sleep(1000);                        //JUST FOR TESTING
              //Insert code for listening
              if(temp == "Pause echo"){
                System.out.println("Gonna pause init");  //JUST FOR TESTING
                Thread.sleep(1000);                      //JUST FOR TESTING
                pause = true;
              }
              while(pause){
                Thread.sleep(3000);                      //JUST FOR TESTING
                System.out.println("Dun pausing mush");  //JUST FOR TESTING
                //Insert code for key word listener
                pause = false;                           //JUST FOR TESTING
                Thread.sleep(1000);                      //JUST FOR TESTING
              }
          }
      }
      catch (Exception e){
        System.out.println("You managed to break it. Good job!");
      }
  }
}
