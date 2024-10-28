package Game.Cards;

public class Charmander extends Pokemon{
    //constructor
    public Charmander(){
        super("Charmander");
        setHp(70);
        setStartingHp(70);
    }
    //attack
    public Boolean flare(Pokemon target){
            //deal 20 damage
        if (this.getEnergyCount() >= 2){
            int currentHp = target.getHp();
            int resultingHp = currentHp - 20;
        
            target.setHp(resultingHp);
            this.removeEnergy(2);
            return true;
        }
        else
            return false;
    }

    
}
