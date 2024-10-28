package Game.Cards;

import Game.Engines.Player;

public class Nemona extends Trainer {
    //constructor
    public Nemona() {
        super("Nemona"); 
    }
    //draws 3 cards
    public void use(Player player) {
       for(int i = 0; i < 3; i++)
        player.drawCard();
    }
}
