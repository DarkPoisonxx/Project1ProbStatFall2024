import java.math.BigInteger;

public class TestPermutations {
    public static void main(String[] args) {
        
        Permutations test = new Permutations();
        Combinations testComb = new Combinations();
        BinomialDistribution testBinomialDistribution = new BinomialDistribution();
        Factorials testFactorials = new Factorials();
        System.out.println("Permuation: " + test.Permutation(5, 4));
        System.out.println("Combination: " + testComb.combination(32, 16));
        System.out.println("Binomial Distribution: " + testBinomialDistribution.binomialDistribution(4, .75, .25, 2));
        System.out.println("RecursiveFactorial: " + testFactorials.RecursiveFactorial(5));
        System.out.println("Recursive BigInt Factorial: " + testFactorials.RecursiveFactorialBigInteger(BigInteger.valueOf(10)));
    }
}
