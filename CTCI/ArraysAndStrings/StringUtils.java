package CTCI.ArraysAndStrings;

public class StringUtils {
    public static void print(String[] list) {
        for (String s : list) {
            System.out.print(s + "\t");
        }
    }

    public static void print(String[][] matrix) {
        for (String[] aMatrix : matrix) {
            for (String anAMatrix : aMatrix) {
                System.out.print(anAMatrix + "\t\t");
            }
            System.out.println();
        }
    }
}
