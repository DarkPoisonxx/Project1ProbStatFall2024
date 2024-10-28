package StatsLibrary.Probability;
public class TestProbability {
    public static void main(String[] args) {

        //Conditional test
        double ConditionalA = .5;
        double ConditionalB = .3;
        ConditionalProbability testConditional = new ConditionalProbability();
        System.out.println("Conditional Probability: " + testConditional.conditionalProbability(ConditionalA,ConditionalB));

        //Determine Dependency Test
        double independentA = .5;
        double independentB = .4;
        double independentAandB = .2;
        DetermineDependency testDependency = new DetermineDependency();
        System.out.println("is independent: " + testDependency.independent(independentA, independentB, independentAandB));
        double dependentA = .5;
        double dependentB = .4;
        double dependentAandB = .5;
        System.out.println("is independent: " + testDependency.independent(dependentA, dependentB, dependentAandB));

        //Intersection Test
        Intersection intersectionTest = new Intersection();
        double conditionalBgivenA = .7;
        System.out.println("Dependent Intersection: " + intersectionTest.depIntersection(dependentA, conditionalBgivenA));  
        System.out.println("Independent Intersection: " + intersectionTest.indepIntersection(independentA,independentB));


    }
}
