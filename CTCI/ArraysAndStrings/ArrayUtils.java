package CTCI.ArraysAndStrings;

import java.util.List;

public class ArrayUtils {
    public static void printMatrix(int[][] matrix) {
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                System.out.print(anAMatrix + "\t\t");
            }
            System.out.println();
        }
    }

    public static void print(int[] ints) {
        System.out.println("Printing array of elements.");
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void print(List<Integer> integers) {
        System.out.println("Printing list of elements.");
        for (int integer : integers) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    public static void printMatrix(char[][] board) {
        for (char[] aMatrix : board) {
            for (char anAMatrix : aMatrix) {
                System.out.print(anAMatrix + "\t\t");
            }
            System.out.println();
        }
    }
}
