import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;

public class PrizeDoor {
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final Random rng = new Random();
    
   
    public boolean prize(boolean switchChoice) {
        
        ArrayList<Boolean> doors = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) 
            doors.add(false); 
        
        
        int prizeDoor = rng.nextInt(3);
        doors.set(prizeDoor, true); 
       
        int playerChoice = rng.nextInt(3); 
        
        ArrayList<Integer> availableDoors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && !doors.get(i)) 
                availableDoors.add(i); 
        }
        
        int doorRemoved = availableDoors.get(rng.nextInt(availableDoors.size()));
        
        if (switchChoice) {
            
            for (int i = 0; i < 3; i++) {
                if (i != playerChoice && i != doorRemoved) {
                    playerChoice = i; 
                    break;
                }
            }
        }
        
        
        return doors.get(playerChoice);
    }
    
    public void percentChance(int iterations) {
        int winsWithoutSwitch = 0;
        int winsWithSwitch = 0;

        for (int i = 0; i < iterations; i++) {
            
            if (prize(false)) 
                winsWithoutSwitch++;
            if (prize(true)) 
                winsWithSwitch++;
        }
        
        double winRateWithoutSwitch = ((double) winsWithoutSwitch / iterations) * 100;
        double winRateWithSwitch = ((double) winsWithSwitch / iterations) * 100;

        System.out.println("Win rate without switching: " + decimalFormat.format(winRateWithoutSwitch) + "%");
        System.out.println("Win rate with switching: " + decimalFormat.format(winRateWithSwitch) + "%");
    }

    public static void main(String[] args) {
        PrizeDoor test = new PrizeDoor();
        test.percentChance(1000000); 
    }
}
