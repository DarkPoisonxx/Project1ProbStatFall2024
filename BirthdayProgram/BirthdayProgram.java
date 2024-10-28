package BirthdayProgram;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BirthdayProgram {

    public boolean isBirthday(int numPeople){
        ArrayList<Person> People = new ArrayList<>();
        for(int i = 0; i < numPeople; i++){
            Person iPerson = new Person();
            People.add(iPerson);
        }
        for(int i = 0; i < People.size() - 1; i++){
            for(int j = i + 1; j < People.size() - 1; j++){
                if(People.get(i).getBirthday() == People.get(j).getBirthday()){
                    return true;
                }
            }
        }
        return false;
    }


    public double chance(int numPeople, int runTimes) {
    int sameBirthdays = 0;
    for (int i = 0; i < runTimes; i++) {
        if (isBirthday(numPeople)) {
            sameBirthdays += 1;
        }
    }
    double percentage = (double) sameBirthdays / runTimes * 100; 

    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    String formattedPercentage = decimalFormat.format(percentage);
    System.out.println("Chance of at least two people sharing a birthday: " + formattedPercentage + "%");
    return percentage; 
}
    public static void main(String[] args) {
        BirthdayProgram test = new BirthdayProgram();
        test.chance(30, 100000);
    }
}

