
package MonteCarlos.numCandy;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CandyChance {

    public void candyChance() {
        int iterations = 1000000; 
        int numPokemon = 10; 

        
        for (int candyCount = 1; candyCount <= 6; candyCount++) {
            double failedRuns = 0;

            
            for (int iteration = 0; iteration < iterations; iteration++) {
                PokemonCardGame game = new PokemonCardGame(numPokemon, candyCount);
               
                int candyInPrizeDeck = countCandyInPrizeDeck(game.getPrizeDeck());

               
                if (candyInPrizeDeck == candyCount) {
                    failedRuns++; 
                }
            }

            
            double failureRate = (failedRuns / iterations) * 100; 

           
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            String failureRateFormatted = decimalFormat.format(failureRate);
            System.out.println("With " + candyCount + " Candy cards, " + failureRateFormatted + "% of runs had all candies in the prize deck.");
        }
    }

    private int countCandyInPrizeDeck(ArrayList<Card> prizeDeck) {
        int count = 0;
        for (Card card : prizeDeck) {
            if (card instanceof Candy) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CandyChance candyTest = new CandyChance();
        candyTest.candyChance();
    }
}
