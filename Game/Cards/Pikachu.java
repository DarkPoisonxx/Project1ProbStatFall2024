
package Game.Cards;

public class Pikachu extends Pokemon {
    //constructor
    public Pikachu(){
        super("Pikachu");
        setHp(70);
        setStartingHp(70);
    }
    
    //attacks fo 20 damage
    public boolean electroBall(Pokemon unfortunatePokemon){
       
        if (this.getEnergyCount() >= 2){
            int currentHp = unfortunatePokemon.getHp();
            int resultingHp = currentHp - 20;
            unfortunatePokemon.setHp(resultingHp);
            this.removeEnergy(1);
        return true;
        }
        else
            return false;
    }
    
}
