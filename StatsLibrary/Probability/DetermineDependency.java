package StatsLibrary.Probability;

public class DetermineDependency {
    
    public boolean independent(double A, double B, double AandB){
        if (A * B == AandB)
            return true;
        return false;
    }
}
