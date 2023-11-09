public class Player implements Runnable {
    private int playerNumber; //not same as num of players,is index of thread


    //constructor to initialise the player with a player number
    public Player(int playerNumber){
        this.playerNumber = playerNumber;
    }

    public void run(){
        //code to be executed by the players thread
        System.out.println("Player "+ playerNumber + " is running. ");

    }
   
}