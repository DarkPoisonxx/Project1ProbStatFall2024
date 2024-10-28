
public class PoissonProbability {
        //finds the poisson Probability 
    public double findPoissonProbability(double variance, long X){
        Factorials factorials = new Factorials();
        double p;
        p = (Math.pow(variance, X) * Math.pow(Math.E, -1 * variance));
        p = p / factorials.RecursiveFactorial(X);
        return p;

    }   
}
