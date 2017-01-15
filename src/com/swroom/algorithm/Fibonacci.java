package com.swroom.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hanoi
 * Created by jingz on 2017/1/13.
 */
public class Fibonacci {
    public static void main(String args[]) throws IOException {
        int n;
        BufferedReader buf;
        buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入要输出的斐波那契个数：");
        n = Integer.parseInt(buf.readLine());
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.printFibonacci(n);
    }

    public void printFibonacci(int n) {
        int[] fib = new int[n];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }

        for (int i = 0; i < fib.length; i++) {
            System.out.print(fib[i] + " ");
        }

        System.out.println();

    }
}
