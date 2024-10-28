package Game.Cards;

public class PokemonNurse extends Trainer {
    //constructor
    public PokemonNurse() {
        super("Pokemon Nurse"); 
    }
    //heals target to full health
    public void use(Pokemon target){
        target.useStartingHp();
    }
}
