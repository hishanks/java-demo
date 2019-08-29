package com.demo.test.matchu;

import java.util.Calendar;

/**
 * @author Shanks
 * @date 2019-08-29
 */
public class Elvis {

    private static final Elvis INSTANCE = new Elvis();
    private final int beltSize;
    /** Calendar.YEAR <=> public final static int YEAR = 1; */
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private Elvis() {
        System.out.println("CURRENT_YEAR: " + CURRENT_YEAR);
        beltSize = CURRENT_YEAR - 1930;
        System.out.println("beltSize: " + beltSize);
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        // Elvis wears size -1930 belt.
        System.out.println("Elvis wears size " + INSTANCE.beltSize() + " belt.");
    }
}