import java.math.BigInteger;

public class Factorials {
    //Recursive method for long Factorial
    public long RecursiveFactorial(long n){
        if(n == 0 || n == 1){
        return 1;
        }
        return n * RecursiveFactorial(n-1);
    }

    //Recursive method for BigInteger Factorial
    public BigInteger RecursiveFactorialBigInteger(BigInteger n){
        if(n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)){
        return BigInteger.ONE;
        }
        return n.multiply(RecursiveFactorialBigInteger(n.subtract(BigInteger.ONE))) ;
    }
}
