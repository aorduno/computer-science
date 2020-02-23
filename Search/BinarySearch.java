package Search;

public class BinarySearch {
    static boolean hasValue(int[] array, int toFound) {
        return hasValueRecursive(array, toFound, 0, array.length - 1);
    }

    static boolean hasValueIterative(int[] array, int toFound) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == toFound) {
                return true;
            }

            if (array[mid] < toFound) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    private static boolean hasValueRecursive(int[] array, int toFound, int start, int end) {
        if (start > end) {
            return false;
        }

        int mid = start + (end - start) / 2;
        if (array[mid] == toFound) {
            return true;
        }

        if (array[mid] < toFound) {
            return hasValueRecursive(array, toFound, mid + 1, end);
        }

        return hasValueRecursive(array, toFound, start, mid - 1);
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 50, 76, 88, 91, 105, 1000};

        // recursive
        System.out.println("RECURSIVE");
        System.out.println("Contains: " + hasValue(numbers, 1));
        System.out.println("Contains: " + hasValue(numbers, 2));
        System.out.println("Contains: " + hasValue(numbers, 3));
        System.out.println("Contains: " + hasValue(numbers, 4));
        System.out.println("Contains: " + hasValue(numbers, 5));
        System.out.println("Contains: " + hasValue(numbers, 6));
        System.out.println("Contains: " + hasValue(numbers, 50));
        System.out.println("Contains: " + hasValue(numbers, 76));
        System.out.println("Contains: " + hasValue(numbers, 88));
        System.out.println("Contains: " + hasValue(numbers, 91));
        System.out.println("Contains: " + hasValue(numbers, 105));
        System.out.println("Contains: " + hasValue(numbers, 1000));

        System.out.println("Contains: " + hasValue(numbers, -1));
        System.out.println("Contains: " + hasValue(numbers, 2000));
        System.out.println("Contains: " + hasValue(numbers, 0));

        // iterative
        System.out.println("ITERATIVE");
        System.out.println("Contains: " + hasValueIterative(numbers, 1));
        System.out.println("Contains: " + hasValueIterative(numbers, 2));
        System.out.println("Contains: " + hasValueIterative(numbers, 3));
        System.out.println("Contains: " + hasValueIterative(numbers, 4));
        System.out.println("Contains: " + hasValueIterative(numbers, 5));
        System.out.println("Contains: " + hasValueIterative(numbers, 6));
        System.out.println("Contains: " + hasValueIterative(numbers, 50));
        System.out.println("Contains: " + hasValueIterative(numbers, 76));
        System.out.println("Contains: " + hasValueIterative(numbers, 88));
        System.out.println("Contains: " + hasValueIterative(numbers, 91));
        System.out.println("Contains: " + hasValueIterative(numbers, 105));
        System.out.println("Contains: " + hasValueIterative(numbers, 1000));

        System.out.println("Contains: " + hasValueIterative(numbers, -1));
        System.out.println("Contains: " + hasValueIterative(numbers, 2000));
        System.out.println("Contains: " + hasValueIterative(numbers, 0));
    }
}
