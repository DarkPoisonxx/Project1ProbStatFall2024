package Game.Engines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Game.Cards.Bulbasaur;
import Game.Cards.Card;
import Game.Cards.Charmander;
import Game.Cards.Energy;
import Game.Cards.Nemona;
import Game.Cards.Pikachu;
import Game.Cards.Pokemon;
import Game.Cards.PokemonNurse;
import Game.Cards.Potion;
import Game.Cards.Trainer;

public class Player implements PlayerAction {

    private String playerName;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> bench;   
    private Random rng;
    private Scanner scanner;
    private Pokemon activePokemon;
    private int mulliganCount;
    private ArrayList<Card> prizeDeck;
    private ArrayList<Card> prizes;

    public Player(String name, int numPokemon) {
        this.playerName = name;
        this.deck = new ArrayList<Card>();
        this.hand = new ArrayList<Card>();
        this.bench = new ArrayList<Card>();
        this.rng = new Random();
        this.prizeDeck = new ArrayList<Card>();
        this.scanner = new Scanner(System.in);
        this.prizes = new ArrayList<Card>();
        
        
        initializeRandomDeck(numPokemon);   // Initialize deck with random Pokemon and Energy cards
        drawPrizeDeck();
        drawInitialHand();                  // Draw the first hand
    }

    private void initializeRandomDeck(int numPokemon) {     
        int deckSize = 60; // Total deck size
        int energyCount = 15; // Number of Energy cards
        int trainerCount = deckSize - numPokemon - energyCount; // Number of Trainer cards to add
    
        
        ArrayList<Pokemon> specificPokemon = new ArrayList<>();
        specificPokemon.add(new Pikachu());
        specificPokemon.add(new Bulbasaur());
        specificPokemon.add(new Charmander());
    
        // Add pokemon to deck
        for (Pokemon pokemon : specificPokemon) {
            deck.add(pokemon);
        }
    
        // Add Energy cards to deck
        for (int i = 0; i < energyCount; i++) {     
            deck.add(new Energy());
        }
    
        // Add random Trainer cards to deck
        for (int i = 0; i < trainerCount; i++) {
            
            Trainer randomTrainer;
            switch (rng.nextInt(3)) { // Randomly choose one of the three Trainers
                case 0:
                    randomTrainer = new Nemona();
                    break;
                case 1:
                    randomTrainer = new PokemonNurse();
                    break;
                case 2:
                    randomTrainer = new Potion();
                    break;
                default:
                    randomTrainer = new Nemona(); //default
                    break;
            }
            deck.add(randomTrainer);
        }
    
        shuffleDeck(); // Shuffle the deck after initialization
    }

    //draws a prizeDeck for a player to protect
    private void drawPrizeDeck() {
        Random random = new Random();
        

        Collections.shuffle(deck, random);

        // Draw 6 cards
        for (int i = 0; i < 6 && !deck.isEmpty(); i++) {
            prizeDeck.add(deck.remove(0)); // Remove the cards
        }
    }
    
    // Remove the top prize card and return it
    public Card getPrize() {
        if (!prizeDeck.isEmpty()) {
            return prizeDeck.remove(0); 
        }
        return null;
    }
    //sets the first pokemon in hand to active
    public void forceActivePokemon() {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card instanceof Pokemon) {
                activePokemon = (Pokemon) card;
                hand.remove(i); // Remove thm from the hand
                System.out.println(playerName + " has set " + activePokemon.getName() + " as the active Pokémon.");
                return;
            }
        }
    }
    //this handles mulligans
    public void addHandToDeck() {
        for (Card card : hand) { 
            deck.add(card); 
        }
            hand.clear();
            this.shuffleDeck();
    }
    
    

    //used to display the status of everything a player has going on and needs to pay attention to
    public void displayStatus(Player opponent) {
        // Display the active Pokemon, its HP, Energy count
        System.out.print("Active: ");
        if (activePokemon != null) {
            System.out.print(activePokemon.getName() + " HP: " + activePokemon.getHp() + " Energy: " + activePokemon.getEnergyCount());
        } else {
            System.out.print("None");
        }
        System.out.println();
    
        // Display opponent active Pokemon
        System.out.print("Opponent's Active: ");
        if (opponent.getActivePokemon() != null) {
            System.out.print(opponent.getActivePokemon().getName() + " HP: " + opponent.getActivePokemon().getHp());
        } else {
            System.out.print("None");
        }
        System.out.println();
    
        // Display bench
        System.out.print("Bench: ");
        if (bench.isEmpty()) {
            System.out.println("None");
        } else {
            for (Card card : bench) {
                if (card instanceof Pokemon) {
                    Pokemon benchPokemon = (Pokemon) card;
                    System.out.print(benchPokemon.getName() + " HP: " + benchPokemon.getHp() + " | ");
                }
            }
            System.out.println();
        }
    
        // Display hand
        System.out.print("Hand: ");
        if (hand.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < hand.size(); i++) {
                Card card = hand.get(i);
                if (card instanceof Pokemon) {
                    System.out.print((i + 1) + ". " + ((Pokemon) card).getName() + " | ");
                } else {
                    System.out.print((i + 1) + ". " + card.getClass().getSimpleName() + " | ");
                }
            }
            System.out.println();
        }
    }
    

    
    public void promptAction(Player opponent) {
        boolean hasAttacked = false; // Track if the player has attacked this turn
    
        while (true) {
            displayStatus(opponent); // Display status at the start of each turn and after every action
    
            System.out.println("What would you like to do?");
            System.out.println("1. Use Trainer card");
            System.out.println("2. Attach Energy to the active Pokémon");
            System.out.println("3. Attack with the active Pokémon");
            System.out.println("4. Switch Active Pokémon");
            System.out.println("5. Add Pokémon from hand to bench");
            System.out.println("6. Skip Turn");
    
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    useTrainer();
                    break;
                case 2:
                    attachEnergy();
                    break;
                case 3:
                    // check if the attack was successful
                    if (attackWithPokemon(opponent)) {
                        hasAttacked = true; // mark that the player has attacked
                    } else {
                        System.out.println("Not enough energy to attack. Choose another action.");
                    }
                    break;
                case 4:
                    switchActivePokemon();
                    break;
                case 5:
                    addPokemonFromHandToBench();
                    break;
                case 6:
                    // Allow the player to skip their turn - this helps with possible infinite loops
                    if (hasAttacked || (activePokemon != null && hand.stream().anyMatch(card -> card instanceof Trainer || card instanceof Energy))) {
                        endTurn();
                        return; // End the turn after skipping
                    } else {
                        System.out.println("You cannot skip your turn unless you have attacked or have valid actions to perform.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
    
            // Allow ending the turn only if the player has attacked or chosen to skip
            if (hasAttacked) {
                break;
            }
        }
    }
    
    // Places pokemon in your bench from your hand
    public void addPokemonFromHandToBench() { // Add a Pokemon from hand to the bench
        System.out.println("Choose a Pokémon from your hand to add to the bench:");
    
        // List all Pokemon in hand
        boolean hasPokemonInHand = false;
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card instanceof Pokemon) {
                System.out.println((i + 1) + ". " + ((Pokemon) card).getName());
                hasPokemonInHand = true;
            }
        }
    
        if (!hasPokemonInHand) {
            System.out.println("No Pokémon in hand to add to the bench.");
            return;
        }
    
        // Get user's choice
        int choice = scanner.nextInt() - 1;
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Pokemon) {
            Pokemon selectedPokemon = (Pokemon) hand.get(choice);
            bench.add(selectedPokemon);
            hand.remove(choice);
            System.out.println(selectedPokemon.getName() + " was added to the bench.");
        } else 
            System.out.println("Invalid choice. Please select a valid Pokémon from your hand.");
    }

    // setter for setting the active Pokemon to null
    public void setActivePokemonToNull() {
        activePokemon = null; // Remove the active Pokémon
        if (!bench.isEmpty()) {
            // Switch to the first Pokemon on the bench, or prompt the player to choose
            activePokemon = (Pokemon) bench.remove(0); // For now, just take the first one
            System.out.println("Switched to " + activePokemon.getName() + " from the bench.");
        } else {
            System.out.println("No Pokémon left on the bench to switch to.");

        }
    }

    // Getter for active Pokémon
    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    // Getter for the bench
    public ArrayList<Card> getBench() {
        return bench;
    }
    
    // Getter for player name
    public String getPlayerName() {
        return playerName;
    }

    //getter for the number of mulligans to know how many drawCards you need
    public int getMulliganCount() {
        return mulliganCount;
    }

    //getter for outputs prizes
    public List<Card> getPrizeDeck() {
        return prizeDeck;
        }

    //indexes mulliganCount
    public void setMulliganCount(){
        mulliganCount++;
    }

    // Draw the initial hand of 7 cards
    public void drawInitialHand() {
        hand.clear(); 
        for (int i = 0; i < 7; i++) {
            hand.add(drawCardFromDeck());
        }
    }

    public void shuffleDeck() {    // Shuffles the deck
        for (int i = 0; i < deck.size(); i++) {
            int randomIndex = rng.nextInt(deck.size());
            Card temp = deck.get(i);
            deck.set(i, deck.get(randomIndex));
            deck.set(randomIndex, temp);
        }
    }

    private Card drawCardFromDeck() {
        if (deck.isEmpty()) {
            System.out.println(playerName + " cannot draw, deck is empty!");
            return null;
        }
        return deck.remove(deck.size() - 1);  
    }

    //displays health after each round or attack
    public void displayHealthAfterRound(Player opponent) {
        // Display the active Pokemon's health for this player
        if (activePokemon != null) {
            System.out.println(playerName + "'s active Pokémon: " + activePokemon.getName() + 
                               " - Health: " + activePokemon.getHp());
        } else {
            System.out.println(playerName + " has no active Pokémon.");
        }
    
        // Display the active Pokemon's health for the opponent
        if (opponent.getActivePokemon() != null) {
            System.out.println(opponent.getPlayerName() + "'s active Pokémon: " + 
                               opponent.getActivePokemon().getName() + 
                               " - Health: " + opponent.getActivePokemon().getHp());
        } else {
            System.out.println(opponent.getPlayerName() + " has no active Pokémon.");
        }
    }

    //turn logic
    @Override
    public void takeTurn(Player opponent) {     
        System.out.println(playerName + "'s turn:");
        drawCard();         
        promptAction(opponent);    
        displayHealthAfterRound(opponent);
        endTurn();          
    }

    @Override
    public void switchActivePokemon() { // Switch active Pokemon with one from the bench
        if (bench.isEmpty()) {
            System.out.println("No Pokémon on the bench to switch with.");
            return;
        }

        // Display the Pokemon on the bench
        System.out.println("Choose a Pokémon from the bench to switch to:");
        for (int i = 0; i < bench.size(); i++) {
            Card card = bench.get(i);
            if (card instanceof Pokemon) {
                System.out.println((i + 1) + ". " + ((Pokemon) card).getName());
            }
        }

        int choice = scanner.nextInt() - 1; // User choice
        if (choice >= 0 && choice < bench.size() && bench.get(choice) instanceof Pokemon) {
            activePokemon = (Pokemon) bench.get(choice);
            bench.remove(choice); // Remove it from the bench
            System.out.println(playerName + " switched active Pokémon to " + activePokemon.getName() + ".");
        } else {
            System.out.println("Invalid choice. Please select a valid Pokémon from the bench.");
        }
    }

    //attack logic for interface
    @Override
    public boolean attackWithPokemon(Player opponent) {
        if (activePokemon == null) {
            System.out.println("No active Pokémon to attack with. Please switch Pokémon.");
            return false;
        }
        
        // Check if the opponent has an active Pokemon
        if (opponent.getActivePokemon() == null) {
            System.out.println("Opponent has no active Pokémon to attack.");
            return false;
        }
    
        // The opponent's active Pokemon
        Pokemon targetPokemon = opponent.getActivePokemon();
    
        boolean attackSuccessful = false;
    
        // Attempt attack based on the active Pokemon type
        if (activePokemon instanceof Pikachu) {
            attackSuccessful = ((Pikachu) activePokemon).electroBall(targetPokemon);
        } else if (activePokemon instanceof Bulbasaur) {
            attackSuccessful = ((Bulbasaur) activePokemon).leechSeed(targetPokemon);
        } else if (activePokemon instanceof Charmander) {
            attackSuccessful = ((Charmander) activePokemon).flare(targetPokemon);
        }
    
        if (!attackSuccessful) {
            return false; // Return false to indicate the attack couldn't happen
        }
    
        // If attack is successful, check if the target Pokémon has fainted
        if (targetPokemon.getHp() < 1) {
            System.out.println(targetPokemon.getName() + " has fainted and is removed from play.");
            opponent.setActivePokemonToNull(); // Set opponent's active Pokémon to null
            Card prize = opponent.getPrize();
            prizes.add(prize);
            System.out.println(this.getPlayerName() + " has been awarded "+ prize.getName() + " from "+ opponent.getPlayerName() + "'s prize deck");
        } else {
            System.out.println(activePokemon.getName() + " attacked " + targetPokemon.getName() + "!");
        }
    
        return true; // Indicate the attack was successful
    }


        // Draw a card from the deck
    @Override
    public void drawCard() {
        Card drawnCard = drawCardFromDeck();
        if (drawnCard != null) {
            hand.add(drawnCard);
            System.out.println(playerName + " drew a card.");
        }
    }


        // End the player's turn
    @Override
    public void endTurn() {
        System.out.println(playerName + "'s turn ended.");
        // Logic for ending the turn can be added here
    }

    // Checks if theres at least one Pokemon
    public boolean evaluateOpeningHand() {
        for (Card singleCard : hand) {
            if (singleCard instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }


        // Play a Pokemon from the hand to the bench
    @Override
    public void playPokemon() {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                Pokemon newPokemon = (Pokemon) card;
                bench.add(newPokemon);
                hand.remove(card);
                // Set the first Pokemon played as active Pokemon
                if (activePokemon == null) {
                    activePokemon = newPokemon;
                    System.out.println(playerName + " played " + newPokemon.getName() + " as active Pokémon.");
                } else {
                    System.out.println(playerName + " played " + newPokemon.getName() + " to the bench.");
                }
                return;
            }
        }
        System.out.println(playerName + " has no Pokémon to play.");
    }


                    // Attach Energy to a active Pokemon
    @Override
    public void attachEnergy() {
        if (activePokemon == null) {
            System.out.println(playerName + " has no active Pokémon to attach Energy to.");
            return;
        }
    
        for (Card card : hand) {
            if (card instanceof Energy) {
                activePokemon.attachEnergy((Energy) card); 
                hand.remove(card);
                System.out.println(playerName + " attached Energy to " + activePokemon.getName() + ".");
                return;
            }
        }
        System.out.println(playerName + " has no Energy to attach.");
    }

    //method to use Trainer logic
    @Override
    public void useTrainer() {
        if (activePokemon == null) {
            System.out.println(playerName + " has no active Pokémon to use Trainer cards on.");
            return;
        }
    
        // List all Trainer cards in hand
        ArrayList<Trainer> trainerCards = new ArrayList<>();
        for (Card card : hand) {
            if (card instanceof Trainer) {
                trainerCards.add((Trainer) card);
            }
        }
    
        if (trainerCards.isEmpty()) {
            System.out.println("No Trainer card available to use.");
            return;
        }
    
        // Display Trainer cards for the player to choose from
        System.out.println("Choose a Trainer card to use:");
        for (int i = 0; i < trainerCards.size(); i++) {
            System.out.println((i + 1) + ". " + trainerCards.get(i).getClass().getSimpleName());
        }
    
        int choice = scanner.nextInt() - 1;
    
        
        if (choice < 0 || choice >= trainerCards.size()) {
            System.out.println("Invalid choice. Please select a valid Trainer card from your hand.");
            return;
        }
    
        Trainer selectedTrainer = trainerCards.get(choice);
        hand.remove(selectedTrainer); // Remove the Trainer card from hand
        // Use trainers function
        if (selectedTrainer instanceof Potion) {
            ((Potion) selectedTrainer).use(activePokemon);
            System.out.println(playerName + " used a Potion on " + activePokemon.getName() + ", restoring 30 HP.");
        } else if (selectedTrainer instanceof PokemonNurse) {
            ((PokemonNurse) selectedTrainer).use(activePokemon);
            System.out.println(playerName + " used Pokemon Nurse on " + activePokemon.getName() + ", restoring some HP.");
        } else if (selectedTrainer instanceof Nemona) {
            ((Nemona) selectedTrainer).use(this);
            System.out.println(playerName + " used Nemona's effect.");
        } else {
            System.out.println("The Trainer card has no usable effect.");
        }
    }
}
