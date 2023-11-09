import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;



public class CardGame {

    // Create a single Scanner instance at the class level to be shared across methods
    private static Scanner scanner = new Scanner(System.in);

    public static List<Integer> cardPack = new ArrayList<>();

    public static Map<Integer, List<Integer>> hands = new HashMap<>();


    public static void packGeneration(int numberOfPlayers,String fileName){
        //This functiomn will genereate a pack of cards based on amount of players
           //it should take in 8n cards n being the total amount of players
           //hence it should take in the amount of players and generate it based on that
   
           try{
               //Create a FileWriter and buffered to write to the new file
               FileWriter fileWriter = new FileWriter(fileName);
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
   
               //create a random object to generate random numbers
               Random random = new Random();
   
               //calculate the total number of random numbers the particular pack will need
               int totalNumbers = 8 * numberOfPlayers;
              
   
               //Generate and write rnadom numbers to the file
               for (int i = 0; i < totalNumbers; i++){
                   int randomNumber = random.nextInt(15); //generates random numbers betweem 0 and 15
                   bufferedWriter.write(Integer.toString(randomNumber));//writting random number as string
                  
                   bufferedWriter.newLine();
               }
   
               //closing the buffredWriter and FileWriter to save changes and release resources
               bufferedWriter.close();
               fileWriter.close();

            } catch(IOException e ){
               e.printStackTrace();}//print any exception that may occur when trying to run this method
           }

    
       
   



    public static String getPackLocation(){

        String locationOfPack = null;
        boolean validPackLocation = false;

        while (!validPackLocation) {
            try {
                System.out.println("Please enter the location of pack to load: ");
                locationOfPack = scanner.next();//reads user as input
                System.out.println("Chosen Pack: " + locationOfPack);
                validPackLocation = true; // Input is valid; exit the loop
            } catch (Exception e) {
                //handle exceptions if the user enters an invalid input
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
        return locationOfPack;
    }


    

    

    public static int getPlayerNum() {
        //Initializing variables to store the number of players and track input validity
        int numberOfPlayers = 0;
        boolean validInput = false;

        //Keep prompting the user until valid number entered
        while (!validInput) {
            try {
                System.out.print("Please enter the number of players: ");
                numberOfPlayers = scanner.nextInt();//reads user as input
                System.out.println("You entered: " + numberOfPlayers);
                validInput = true; // Input is valid; exit the loop
            } catch (Exception e) {
                //handle exceptions if the user enters an invalid input
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
        return numberOfPlayers;
    }



    public static Map<Integer, List<Integer>> packDistributiion(List<Integer> cardPack, int numberOfPlayers, int numberOfCardsPerPlayer) {


        /*
         * This method should distribute cards given from a text file hence 
         * should take in the textfile name based on amount of players.
         * Then it should distribute in a round robin styleit should give it to each players deck
        */

        BufferedReader reader = null; 

        /* A BufferedReader wraps around a standard reser object and stores everyting read
           in a Buffer which improves efficiency.
        */ 

        
        try {
            reader = new BufferedReader(new FileReader(getPackLocation()));
        
        



        String line;
        while ((line = reader.readLine()) != null) {
            int cardValue = Integer.parseInt(line.trim()); //trim() removes whitespaces and end of line symbols. parseInt() parses a string to an Int
            cardPack.add(cardValue);

        }}catch(IOException e) {
            System.out.println("Invalid pack file. Please provide a valid pack file.");
        }

        for (int i = 1; i <= numberOfPlayers; i++) {
            hands.put(i, new ArrayList<>());
        }

        for (int i = 0; i < numberOfCardsPerPlayer; i++) {
            for (int currentPlayer = 1; currentPlayer <= numberOfPlayers; currentPlayer++) {
                hands.get(currentPlayer).add(cardPack.remove(0));
            }
        }

        return hands;
    }


    public static void createThread() {
        //Grabs number of players
        int numberOfPlayers = getPlayerNum();

        //Creates a loop that iterates through each player
        for (int i = 1; i<= numberOfPlayers; i++){

            //Create a player instance for the current player
            Player player = new Player(i);
            //creates a new thread for each player using this class
            Thread playerThread = new Thread(player);
            //Set the name of the thread based on the players number
            playerThread.setName("player" + i + "Thread");
            playerThread.start();
        }
         
    }


    public static void drawCard(int playerNumber){
        List<Integer> tempDeck = (CardDeck.decks.get(1));
        int topCard = tempDeck.get(tempDeck.size() - 1);
        System.out.println(tempDeck);
        System.out.println(topCard);
        List<Integer> tempHand = hands.get(playerNumber);
        tempHand.add(topCard);
        System.out.println(hands);
    
    }

    public static void discardCard(int playerNumber){
        hands.get(playerNumber);
        int prefferedNumber = playerNumber;
        Boolean notFavoured = false;

        int tempCard = hands.get(playerNumber).remove(playerNumber);
        
        System.out.println(tempCard);
        System.out.println(hands);
        CardDeck.decks.put(playerNumber + 1, tempCard);





}
        

        


    

        
    //


    




        




    


public static void main(String[] args) {

    //range of the random number of cards in a pack
       
    //Requests number of players in game
    int numberOfPlayers = getPlayerNum();

    //dynamically generating the filename based on the number of players
    String fileName = numberOfPlayers + ".txt";

    //generates the txt pack file with random numbers in
    System.out.println("Random numbers have been written to: " + fileName);


    packGeneration(numberOfPlayers, fileName);

    createThread();

    //Request loaction of pack to load
    String PackLocation = getPackLocation();

    System.out.println(packDistributiion(cardPack, numberOfPlayers, numberOfPlayers));
    System.out.println(CardDeck.populateDecks(numberOfPlayers, cardPack ));

    //drawCard(1);

    discardCard(1);

    




    


}}