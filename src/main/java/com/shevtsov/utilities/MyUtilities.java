package com.shevtsov.utilities;

import java.util.Scanner;

public class MyUtilities {
    private static Scanner sc = new Scanner(System.in);

    public static String inputString() {
        return sc.nextLine();
    }

    public static long inputLong() {
        while (true){
            if (sc.hasNextLong()){
                return sc.nextLong();
            } else {
                System.out.println("Invalid input!!!");
            }
        }
    }

    public static boolean isConfirmed() {
        while (true) {
            System.out.print("(y/n):");
            String input = sc.nextLine();
            if (input.equals("y")){
                return true;
            } else if (input.equals("n")){
                return false;
            } else {
                System.out.println("Invalid input!!!");
            }
        }
    }
}