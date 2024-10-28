
public class CentralTendancies {
    // Finds the Mean
    public double findMean(double[] mySampleNumbers){
        double sum = 0;
        for(int i = 0; i < mySampleNumbers.length; i++){
            sum = mySampleNumbers[i] + sum;
        }
        return sum / mySampleNumbers.length;
    }
    
    // Finds the Median
    public double findMedian(double[] mySampleNumbers){
        int length = mySampleNumbers.length;
        if(length % 2 == 0){
            int FirstMiddle = length / 2;
            return (mySampleNumbers[FirstMiddle] + mySampleNumbers[FirstMiddle + 1])/ 2;
        }
        else 
            length -= 1;
            length = length/2;
            return mySampleNumbers[(length + 1)];  
    }
    
    // Finds the Mode 
    public double findMode(double[] mySampleNumbers){
        double max = 1;
        double mode = mySampleNumbers[0];
        int multipleModes = 1;
        for(int i = 0; i < mySampleNumbers.length; i++){
            int occurance = 1;
            for( int j = i + 1; j < mySampleNumbers.length; j++){
                if (mySampleNumbers[i] == mySampleNumbers[j])
                    occurance += 1;
            }
            if (occurance > max){
                mode = mySampleNumbers[i];
                max = occurance;
            }
            else if (occurance == max){
                 multipleModes = occurance;
            }
        }
            // Returns null for multiple modes
        if(multipleModes == max){
            return Double.NaN;
        }
        else
        return mode;
    
    }
}