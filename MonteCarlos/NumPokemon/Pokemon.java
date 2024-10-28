package MonteCarlos.NumPokemon;



public class Pokemon extends Card
{
    public Pokemon() {
       super("Pokemon");
        
    }

    private int hp;
    
    public int getHp(){
        return hp;
    }
    
    public void setHp(int userInputHp){
        hp = userInputHp;
    }
}
