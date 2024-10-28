package Game.Cards;

public class Potion extends Trainer {
    
    public Potion() {
        super("Potion");
    }

    //heals for 30 health
    public void use(Pokemon target) {
        target.addHp(30); 
    }
}
