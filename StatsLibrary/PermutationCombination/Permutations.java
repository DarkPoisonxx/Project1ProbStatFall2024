

public class Permutations {
    //Method for permutation
    public double Permutation(int n, int r){
        int numerator = 1;
        int denom = 1;
        for(int i = n; i > 1; i--){
            numerator*= i;
        }
        for(int j = n - r; j > 1; j--){
            denom *= j;
        }
        return numerator/denom;
    }
}
