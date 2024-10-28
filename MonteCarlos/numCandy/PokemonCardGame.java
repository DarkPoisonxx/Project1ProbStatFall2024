package MonteCarlos.numCandy;

import java.util.ArrayList;
import java.util.Collections;

public class PokemonCardGame {
    private ArrayList<Card> deck; 
    private ArrayList<Card> prizeDeck;

    // Constructor for the candy test
    public PokemonCardGame(int numPokemon, int candyCount) {
        deck = new ArrayList<Card>();
        prizeDeck = new ArrayList<Card>();
        
        // Add Pokemon cards to the deck
        for (int i = 0; i < numPokemon; i++) {
            deck.add(new Pokemon());
        }
        
        // Add Candy cards to the deck
        for (int i = 0; i < candyCount; i++) {
            deck.add(new Candy("Candy"));
        }

        // Fill the rest of the deck with Energy cards to make 60 total
        int deckSize = 60;
        for (int i = 0; i < deckSize - numPokemon - candyCount; i++) {
            deck.add(new Energy());
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Initialize prize deck
        initializePrizeDeck();
    }

    private void initializePrizeDeck() {
        for (int i = 0; i < 6 && !deck.isEmpty(); i++) {
            prizeDeck.add(deck.remove(0)); // Remove the cards for the prize deck
        }
    }

    public ArrayList<Card> getPrizeDeck() {
        return prizeDeck;
    }
}
