package com.example.student;

import java.math.BigInteger;

public class Factorial {

    static BigInteger factorial(BigInteger n){

        if (n.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        else {
            BigInteger b = n.subtract(BigInteger.ONE);
            return (n.multiply(factorial(b)));
        }
    }
    public static void main(String args[]){
        BigInteger fact= BigInteger.ONE;
        int number=25;//It is the number to calculate factorial
        for (int j = 0; j <= 25; j++) {
            fact = factorial(BigInteger.valueOf(j));
            System.out.println("Factorial of "+j+" is: "+fact);
        }

    }
}
