package Game.Cards;

public class Pokemon extends Card {
    private int hitPoints;
    private int energy;
    private int startingHp;
    

    // Constructor 
    public Pokemon(String name) {
        super(name);  
        this.hitPoints = 100;  
        this.energy = 0;     
    }
    //sets the starting Hp on initialization
    public void setStartingHp(int input){
        startingHp = input;
    }
    //function for healing cards to set to original health
    public void useStartingHp(){
        hitPoints = startingHp;
    }
    //lets you set a specific health
    public void setHp(int input) {
        hitPoints = input;
    }
    //adds a certain number of Hp to current amount
    public void addHp(int input){
        hitPoints += input;
    }
    //returns the current Hp
    public int getHp() {
        return hitPoints;
    }
    //attaches an energy to the pokemon
    public void attachEnergy(Energy card) {
        this.energy +=1;
    }
    //returns the amount of energy a pokemon has
    public int getEnergyCount() {
        return  this.energy;
    }
    // removes energy, for after an attack or trainers
    public void removeEnergy(int numEnergy){
        energy -= numEnergy;
    } 

   
}
