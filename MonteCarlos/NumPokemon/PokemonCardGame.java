package MonteCarlos.NumPokemon;


import java.util.ArrayList;
import java.util.Random;


public class PokemonCardGame
{
    //A deck of cards.
    private ArrayList<Card> deck; // This is the constructors job. = new Cards[];
    private ArrayList<Card> hand;
    private ArrayList<Card> prizeDeck;
    public PokemonCardGame(int numPokemon){
        deck = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        
        for (int i = 0; i < numPokemon; i++){
            deck.add(new Pokemon());
        }
        int deckSize = 60;
        for(int i = 1; i < deckSize - numPokemon; i++){
            deck.add(new Energy());
        }
    }
    // for Candy test
    public PokemonCardGame(int numPokemon, int Candy){
        deck = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        prizeDeck = new ArrayList<Card>();
        for (int i = 0; i < 6 && !deck.isEmpty(); i++) {
            prizeDeck.add(deck.remove(0)); // Remove the cards
        }
        for (int i = 0; i < numPokemon; i++){
            deck.add(new Pokemon());
        }
        int deckSize = 60;
        for(int i = 1; i < deckSize - numPokemon; i++){
            deck.add(new Energy());
        }
        
        
    }
    
    public Card drawCard(){
        Random rng = new Random();
        int cardIndex = rng.nextInt(deck.size());     //Find random Card
        Card drawnCard = deck.get(cardIndex);
        deck.remove(cardIndex);
        return drawnCard;
    }
    
    public void drawHand(){
        for(int i = 0; i < 7; i++){ //counting to 7
            hand.add(drawCard());
        }
    }
    
    public boolean evaluateOpeningHand(){
        for(Card singleCard: hand){
            if(singleCard instanceof Pokemon){
                return true;
            }
        }
        return false;
    }
    
    public boolean openingHandTest(){
        drawHand();
        if(evaluateOpeningHand() == true){
            return true;    
        }
        return false;
    }
    //make engine for program
    public void run(){
                drawHand();
                System.out.println("Has pokemon: " + evaluateOpeningHand());       
    }
    
}