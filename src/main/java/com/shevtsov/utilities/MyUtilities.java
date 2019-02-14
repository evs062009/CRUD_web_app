package com.shevtsov.utilities;

import java.util.Scanner;

public class MyUtilities {
    private static Scanner sc /*= new Scanner(System.in)*/;

    public static String inputString() {
        sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static long inputLong() {
        while (true){
            sc = new Scanner(System.in);
            if (sc.hasNextLong()){
                return sc.nextLong();
            } else {
                System.out.println("Invalid input!!! Try again.");
            }
        }
    }
}