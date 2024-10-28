package Game.Cards;

public class Bulbasaur extends Pokemon
{
    //constructor for Bulbasaur
    public Bulbasaur(){
        super("Bulbasaur");
        setHp(70);
        setStartingHp(70);
    }
    //attack 
    public boolean leechSeed(Pokemon target){
            //deals 20 damage
        if (this.getEnergyCount() >= 2){
            int currentHp = target.getHp();
            int resultingHp = currentHp - 20;
            
            
            int thisCurrentHp = this.getHp();
            this.setHp(thisCurrentHp + 20);
        
            target.setHp(resultingHp);
            this.removeEnergy(2);
            return true;
        }
        else
            return false;
    }
    
}
