
public class TestVariances {
    public static void main(String[] args) {
        double[] x = {1,2,3,4,5,6};
        System.out.println("The varaince of: " );
        Variance.printArray(x);
        double variance;
        //Find Variance Test
        variance = Variance.findVariance(x,3);
        System.out.println("\n" +  "is: " + variance);

        //find Expected Test
        System.out.println(Expected.findExpected(x));
        StandatdDeviation test = new StandatdDeviation();
        //Find Deviation Test
        System.out.println("Deviation: " + test.findDeviation(variance));
        System.out.println("Deviation: " + test.findDeviation(x,3));
    }   
}
