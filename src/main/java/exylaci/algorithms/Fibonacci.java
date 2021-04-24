package exylaci.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    int previous = 0;
    int actual = 0;
    int fibonacci;
    StringBuilder temp = new StringBuilder();
    List<Integer> fibonacciNumbers=new ArrayList<>();

    public String getPrims(int i) {

        if(i<0){
            throw new IllegalArgumentException("Invalid parameter!");
        }

        String result = "";

        generateFibonacci(i, temp);

        result = temp.toString();
        return result;
    }

    private void generateFibonacci(int i, StringBuilder temp) {
        for (int j = 0; j< i; j++){
            fibonacci = previous + actual;

            boolean isPrime = isPrime(fibonacci);

            if(isPrime){
                if(temp.length()>0){
                    temp.append(", ");
                }
                temp.append(fibonacci);
                fibonacciNumbers.add(fibonacci);
            }
            previous = actual;
            actual = fibonacci;

            if(actual==0){
                actual=1;
            }
        }
    }

    private boolean isPrime(int fibonacci) {
        if(fibonacci<2){
            return false;
        }
        boolean isPrime = true;
        for(int k = 2; k< fibonacci; k++){
            if (fibonacci % k == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public List<Integer> getPiecesPrims(int i) {
        while (fibonacciNumbers.size()<i) {
            generateFibonacci(i, temp);
        }
        return fibonacciNumbers;
    }
}

//- Vesszővel elválasztva add vissza egy sztringben az első x darab Fibonacci szám közül a prím számokat!
//- Add vissza egy listában az első x darab prim Fibonacci számot!
