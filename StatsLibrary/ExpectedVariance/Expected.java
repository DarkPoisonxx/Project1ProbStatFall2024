
public class Expected {

    
    public static double findExpected(double[] x){
        double expected = 0;
        for (int i = 0;i< x.length ;i++){
            expected += x[i];
        }
        expected /= x.length;
        return expected;

    }
}
