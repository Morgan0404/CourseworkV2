import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CardDeck {

    private Map<Integer, List<Integer>> deck;
    public static CardDeck decks = new CardDeck();

    public CardDeck() {
        // Initialize the deck as a HashMap
        this.deck = new HashMap<>();
    }

    // Add a player to the deck
    public void addPlayer(int playerId) {
        deck.put(playerId, new ArrayList<>());
    }

    // Add a card to a specific player
    public void addCardToPlayer(int playerId, int card) {
        if (deck.containsKey(playerId)) {
            deck.get(playerId).add(card);
        } else {
            System.out.println("Player ID not found in the deck.");
        }
    }

    // Get the entire deck
    public Map<Integer, List<Integer>> getDeck() {
        return deck;
    }

    public static Map<Integer, List<Integer>> populateDecks(int numberOfPlayers, List<Integer> cardPack) {

        // Add players to the deck
        for (int i = 1; i <= numberOfPlayers; i++) {
            decks.addPlayer(i);
        }

        // Distribute cards to players in a round-robin fashion
        for (int i = 0; i < 4; i++) {
            for (int currentDeck = 1; currentDeck <= numberOfPlayers; currentDeck++) {
                decks.addCardToPlayer(currentDeck, cardPack.remove(0));
            }
        }

        return decks.getDeck();

    }

    
    public List<Integer> get(int i) {
        return deck.get(i);
    }

    public void put(int i, int tempCard) {
    }
}
    






