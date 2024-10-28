
import java.util.ArrayList;

public class SetOperations {

    

    public ArrayList<Double> intersection(ArrayList<Double> set1, ArrayList<Double> set2){
        ArrayList<Double> finalSet = new ArrayList<>();
        for(int i = 0; i < set1.size(); i++){
            for (int j = 0; j < set2.size(); j++){
                if(set1.get(i).equals(set2.get(j))){
                    finalSet.add(set1.get(i));
                }
            }
        }
        return finalSet;
    }

    public ArrayList<Double> union(ArrayList<Double> set1, ArrayList<Double> set2){
        ArrayList<Double> finalSet = new ArrayList<>();
        for(int i = 0; i < set1.size(); i++){
            for (int j = 0; j < set2.size(); j++){
                if(set1.get(i) < (set2.get(j)) && !finalSet.contains(set1.get(i))){
                    finalSet.add(set1.get(i));
                }
                else if(set1.get(i).equals(set2.get(j)) && !finalSet.contains(set1.get(i))){
                    finalSet.add(set1.get(i));
                }
                else if (set1.get(i) > set2.get(j) && !finalSet.contains(set2.get(j))) {
                    finalSet.add(set2.get(j));
                }
            }
        }
        return finalSet;
    }




}
