public class BinomialDistribution {
    

    public double binomialDistribution(int n, double p, double q, int x){
        Combinations combination = new Combinations();
        double product;
        product = (double) combination.combination(n,x)* Math.pow(p, x)*Math.pow(q,n-x);
        return product;
    }
}
