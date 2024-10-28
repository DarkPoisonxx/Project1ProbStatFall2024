package BirthdayProgram;
import java.util.Random;

public class Person {
    Random rnd = new Random();
    private int birthday = 0;
    public Person(){
         birthday = rnd.nextInt(365) + 1;
    }
    public int getBirthday(){
        return birthday;
    }
}
