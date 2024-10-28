package Game.Engines;

public class GameEngine {
    //variables for the 2 players
    private Player player1;
    private Player player2;

    //method fo the start of a game
    public void startGame(String player1Name, String player2Name, int numPokemon1, int numPokemon2) { //numPokemon was more for the MonteCarlo testing
        player1 = new Player(player1Name, numPokemon1);
        player2 = new Player(player2Name, numPokemon2);
    
        System.out.println("Game started between " + player1Name + " and " + player2Name + "\n");
    
        //calling method for mulligans and places the starting pokemon to have a ready field
        performMulligan(player1);
        performMulligan(player2);
        drawExtraCards();
        player1.forceActivePokemon();
        player2.forceActivePokemon();
        
        //logic loop to keep the game going
        takeTurns();
    }
    
    // this is for drawing cards after the mulligans
    private void drawExtraCards() {
        drawCardsForMulligan(player1, player2);
        drawCardsForMulligan(player2, player1);
    }
    //helper method
    private void drawCardsForMulligan(Player player, Player opponent) {
        for (int i = 0; i < opponent.getMulliganCount(); i++) {
            player.drawCard();
        }
    }
    //method that checks for mulligans
    private void performMulligan(Player player) {
        while (!player.evaluateOpeningHand()) {
            System.out.println(player.getPlayerName() + " has no Pokémon in hand. Performing a mulligan.");
            player.addHandToDeck();
            player.drawInitialHand();
            player.setMulliganCount(); // Changed to increment directly
        }
        System.out.println(player.getPlayerName() + "'s hand is valid.");
    }

    //method that will allow me to keep methods within GameEngine
    private void takeTurns() {
        while (true) {
            if (takeTurnForPlayer(player1, player2) || takeTurnForPlayer(player2, player1)) {
                break;
            }
        }
    }
    // takes the turn and checks for win conditions
    private boolean takeTurnForPlayer(Player currentPlayer, Player opponent) {
        currentPlayer.takeTurn(opponent);
    
        //checks win conditions
        if (checkForWinner(currentPlayer, opponent)) {
            return true; // game ends
        }
        
        return false; // contnues the game
    }
    
    private boolean checkForWinner(Player player, Player opponent) {
        // win condition 1: opponent has no Pokemon in active slot or bench
        if (opponent.getActivePokemon() == null && opponent.getBench().isEmpty()) {
            System.out.println(player.getPlayerName() + " wins! " + opponent.getPlayerName() + " has no Pokémon left.");
            return true;
        }
    
        // win condition 2: opponent has no cards left in their prize deck
        if (opponent.getPrizeDeck().isEmpty()) {
            System.out.println(player.getPlayerName() + " wins by collecting all prizes!");
            return true;
        }
    
        return false; // No winner
    }
}
