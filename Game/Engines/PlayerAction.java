package Game.Engines;

public interface PlayerAction {
    void drawCard();           // The player draws a card
    void playPokemon();        // The player plays a Pokémon to the field
    void attachEnergy();       // The player attaches an Energy to a Pokemon
    void endTurn();            // The player ends their turn
    boolean attackWithPokemon(Player opponent); //players active pokemon attacks opponents active Pokemon
    void setActivePokemonToNull();      // sets the active to nothing
    void switchActivePokemon();         // switches active
    void useTrainer();
    void takeTurn(Player opponent);
}
