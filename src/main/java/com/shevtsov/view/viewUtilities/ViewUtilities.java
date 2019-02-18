package com.shevtsov.view.viewUtilities;

import java.util.List;
import java.util.Scanner;

public class ViewUtilities {
    private static Scanner sc;

    public static String inputString() {
        sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static long inputLong() {
        while (true) {
            sc = new Scanner(System.in);
            if (sc.hasNextLong()) {
                return sc.nextLong();
            } else {
                System.out.println("Invalid input!!! Try again.");
            }
        }
    }

    public static int inputInt() {
        while (true) {
            sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Invalid input!!! Try again.");
            }
        }
    }

    public static void showList(List list) {
        if (!list.isEmpty()){
            for (Object object : list) {
                System.out.println(object);
            }
        } else {
            System.out.println("List is empty!!!");
        }
    }
}