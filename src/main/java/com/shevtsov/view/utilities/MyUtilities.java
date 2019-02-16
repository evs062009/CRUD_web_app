package com.shevtsov.view.utilities;

import java.util.Scanner;

public class MyUtilities {
    private static Scanner sc /*= new Scanner(System.in)*/;

    public static String inputString() {
        sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static long inputLong() {
        while (true) {

            //вместо цикла попробовать использовать рекурсию
            sc = new Scanner(System.in);
            if (sc.hasNextLong()) {
                return sc.nextLong();
            } else {
                System.out.println("Invalid input!!! Try again.");
            }
        }
    }
}