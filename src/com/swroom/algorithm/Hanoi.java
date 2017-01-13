package com.swroom.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hanoi
 * Created by jingz on 2017/1/13.
 */
public class Hanoi {
    public static void main(String args[]) throws IOException {
        int n;
        BufferedReader buf;
        buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入盘数：");
        n = Integer.parseInt(buf.readLine());
        Hanoi hanoi = new Hanoi();
        hanoi.move(n, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔核心算法
     * @param n 盘子数
     * @param a 柱子一
     * @param b 柱子二
     * @param c 柱子三
     */
    public void move(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("盘 " + n + " 由 " + a + " 移至 " + c);
        } else {
            move(n - 1, a, c, b);
            System.out.println("盘 " + n + " 由 " + a + " 移至 " + c);
            move(n - 1, b, a, c);
        }
    }
}
