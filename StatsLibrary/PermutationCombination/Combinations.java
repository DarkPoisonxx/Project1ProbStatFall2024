
public class Combinations {
    
    public long combination(int n, int r){
        Factorials factorial = new Factorials();
        
        long product = factorial.RecursiveFactorial((long)n)/((factorial.RecursiveFactorial((long)r))*(factorial.RecursiveFactorial((long)n-r)));

        return product;
    }

    
}
