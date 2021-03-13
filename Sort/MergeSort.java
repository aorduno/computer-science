package Sort;

public class MergeSort {
    static void mergeSort(int[] array) {
        mergeSort(array, new int[array.length], 0, array.length - 1);
    }

    static void mergeSort(int[] array, int[] temp, int start, int end) {
        System.out.println("processing megesort from " + start + " to " + end);
        if (start >= end) {
            System.out.println("we are done!");
            return;
        }

        int middle = (start + end) / 2;
        mergeSort(array, temp, start, middle);
        mergeSort(array, temp, middle + 1, end);
        mergeHalves(array, temp, start, end);
    }

    static void mergeHalves(int[] array, int[] temp, int leftStart, int rightEnd) {
        System.out.println("merging halves from " + leftStart + " to " + rightEnd);
        // 1) get a mid point
        // 2) divide array in halves...
        // 3) compare left with right and increase indexes one by one
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;
        int left = leftStart;
        int right = rightStart;
        int index = leftStart;
        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index] = array[left];
                left++;
            } else {
                temp[index] = array[right];
                right++;
            }

            index++;
        }

        // either left or right are still pending do merge those in the temp array
        System.arraycopy(array, left, temp, index, leftEnd - left + 1);
        System.arraycopy(array, right, temp, index, rightEnd - right + 1);
        System.out.println("Temp array at this point");
        print(temp);
        System.arraycopy(temp, leftStart, array, leftStart, rightEnd - leftStart + 1);
    }

    public static void mergeSort(char[] array) {
        mergeSort(array, new char[array.length], 0, array.length - 1);
    }

    static void mergeSort(char[] array, char[] temp, int start, int end) {
        System.out.println("processing megesort from " + start + " to " + end);
        if (start >= end) {
            System.out.println("we are done!");
            return;
        }

        int middle = (start + end) / 2;
        mergeSort(array, temp, start, middle);
        mergeSort(array, temp, middle + 1, end);
        mergeHalves(array, temp, start, end);
    }

    static void mergeHalves(char[] array, char[] temp, int leftStart, int rightEnd) {
        System.out.println("merging halves from " + leftStart + " to " + rightEnd);
        // 1) get a mid point
        // 2) divide array in halves...
        // 3) compare left with right and increase indexes one by one
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;
        int left = leftStart;
        int right = rightStart;
        int index = leftStart;
        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index] = array[left];
                left++;
            } else {
                temp[index] = array[right];
                right++;
            }

            index++;
        }

        // either left or right are still pending do merge those in the temp array
        System.arraycopy(array, left, temp, index, leftEnd - left + 1);
        System.arraycopy(array, right, temp, index, rightEnd - right + 1);
        System.out.println("Temp array at this point");
        print(temp);
        System.arraycopy(temp, leftStart, array, leftStart, rightEnd - leftStart + 1);
    }

    static void print(int[] array) {
        System.out.print("Printing array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void print(char[] array) {
        System.out.print("Printing array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] numbers1 = {100, 10, 5, 3, 1};
//        printMatrix(numbers1);
//        mergeSort(numbers1);
//        System.out.println("Sorted");
//        printMatrix(numbers1);

        int[] numbers2 = {100, 50, 1, 3, 5, 1000, 15};
        print(numbers2);
        mergeSort(numbers2);
        System.out.println("Sorted");
        print(numbers2);

/*
        int[] numbers3 = {1, 3, 5};
        printMatrix(numbers3);
        mergeSort(numbers3);
        System.out.println("Sorted");
        printMatrix(numbers3);

        int nums[] = {7, -5, 3, 2, 1, 0, 45};
*/


    }
}
