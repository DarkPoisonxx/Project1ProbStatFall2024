
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Double> set1 = new ArrayList<>();
        ArrayList<Double> set2 = new ArrayList<>();
        set1.add(1.0);
        set1.add(2.0);
        set1.add(3.0);
        set1.add(4.0);
        set2.add(1.0);
        set2.add(2.5);
        set2.add(3.5);
        set2.add(4.0);
        System.out.println("Set Union");
        System.out.println(set1);
        System.out.println(set2);
        SetOperations test = new SetOperations();
        System.out.println((test.intersection(set1,set2)));
        System.out.println(test.union(set1, set2));
    }
}
