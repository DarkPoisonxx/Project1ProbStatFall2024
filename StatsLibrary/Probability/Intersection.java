package StatsLibrary.Probability;

public class Intersection {
    public double depIntersection(double A, double conditionalBgivenA){
       return A * conditionalBgivenA;
    }

    public double indepIntersection(double A, double B){
        return A*B;
    }
}
