
public class TestCentralTendancies {
   
    public static void main(String[] args){
        CentralTendancies test = new CentralTendancies();
        
        double[] mySampleNumbers = {1,1,2,2,2,3};
        double storeResults = test.findMean(mySampleNumbers);
        double medianResults = test.findMedian(mySampleNumbers);
        double modeResults = test.findMode(mySampleNumbers);
        System.out.println("The average is: " + storeResults);
        System.out.println("The Median is: " + medianResults);
        System.out.println("the Mode is: " + modeResults);
        
    }
    
    

}
