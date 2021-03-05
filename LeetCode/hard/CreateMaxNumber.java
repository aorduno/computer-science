package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateMaxNumber {
    public static void main(String[] args) {
        printNow();
        testCase(new int[]{7, 6, 1, 9, 3, 2, 3, 1, 1}, new int[]{4, 0, 9, 9, 0, 5, 5, 4, 7}, 9);
        printNow();
        testCase(new int[]{2, 2, 0, 6, 8, 9, 6}, new int[]{5, 2, 4, 5, 3, 6, 2}, 7);
        printNow();
        testCase(new int[]{1, 2}, new int[]{}, 2);
        printNow();
        testCase(new int[]{}, new int[]{1}, 1);
        printNow();
        testCase(new int[]{}, new int[]{}, 0);
        printNow();
        testCase(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5);
        printNow();
        testCase(new int[]{6, 7}, new int[]{6, 0, 4}, 5);
        printNow();
        testCase(new int[]{3, 9}, new int[]{8, 9}, 3);
        printNow();
        testCase(new int[]{3, 9}, new int[]{8, 9}, 4);
        printNow();
        testCase(new int[]{2, 5, 6, 4, 4, 0}, new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15);
        printNow();
        testCase(new int[]{8, 5, 9, 5, 1, 6, 9}, new int[]{2, 6, 4, 3, 8, 4, 1, 0, 7, 2, 9, 2, 8}, 20);
        printNow();
        testCase(new int[]{8, 5, 9, 5, 2, 6, 4, 3, 8, 4, 1, 6, 9, 1, 0, 7, 2, 9, 2, 8}, new int[]{8, 5, 9, 5, 2, 6, 4, 3, 8, 4, 1, 6, 9, 1, 0, 7, 2, 9, 2, 8}, 40);
        printNow();
    }

    private static void printNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        LogUtils.logMessage(formatter.format(date));
    }

    private static void testCase(int[] m, int[] n, int k) {
        LogUtils.logMessage("[[CreateMaxNumber]] Creating maximum number for give inputs...");
        LogUtils.logMessage("m:");
        ArrayUtils.print(m);
        LogUtils.logMessage("n:");
        ArrayUtils.print(n);
        LogUtils.logMessage("k:" + k);

        int[] max = computeMaxNumberDP(m, n, k);
        LogUtils.logMessage("Result:");
        ArrayUtils.print(max);
    }

    // DP
    private static int[] computeMaxNumberDP(int[] m, int[] n, int k) {
        Map<String, int[]> memoM = createMemo(m, k);
        for (int x = 2; x <= k; x++) {
            computeMaxByLengthDp(m, x, memoM);
        }

        Map<String, int[]> memoN = createMemo(n, k);
        for (int x = 2; x <= k; x++) {
            computeMaxByLengthDp(n, x, memoN);
        }

        return computeMaxDp(m, n, memoM, memoN, k);
    }

    private static Map<String, int[]> createMemo(int[] n, int k) {
        Map<String, int[]> memo = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int x = n.length - 1; x >= 0; x--) {
            memo.put(x + "_" + 0, new int[]{});
            max = Math.max(n[x], max);
            memo.put(x + "_" + 1, new int[]{max});
        }

        return memo;
    }

    private static void computeMaxByLengthDp(int[] nums, int k, Map<String, int[]> memo) {
        for (int x = nums.length - k; x >= 0; x--) {
            int[] previousKeyMax = getMemoValue((x + 1) + "_" + k, k, memo, x);
            int[] previousLengthMax = getMemoValue((x + 1) + "_" + (k - 1), k, memo, x);
            int[] memoValue = memo.getOrDefault(x + "_" + k, new int[k]);
            int currentNum = nums[x];
            if (currentNum >= previousKeyMax[0]) {
                memoValue[0] = currentNum;
                System.arraycopy(previousLengthMax, 0, memoValue, 1, previousLengthMax.length);
            } else {
                System.arraycopy(previousKeyMax, 0, memoValue, 0, previousKeyMax.length);
            }

            memo.put(x + "_" + k, memoValue);
        }
    }

    private static int compare(int[] a, int indexA, int[] b, int indexB) {
        while (indexA < a.length && indexB < b.length) {
            int currentA = a[indexA];
            int currentB = b[indexB];
            if (currentA != currentB) {
                return currentA - currentB > 0 ? 1 : -1;
            }

            indexA++;
            indexB++;
        }

        if (indexA < a.length || indexB < b.length) {
            int leftA = a.length - indexA;
            int leftB = b.length - indexB;
            return leftA - leftB > 0 ? 1 : -1;
        }

        return 0;
    }


    private static int[] getMemoValue(String key, int k, Map<String, int[]> memo, int x) {
        return memo.getOrDefault(key, new int[]{0});
    }

    private static int[] computeMaxDp(int[] m, int[] n, Map<String, int[]> memoM, Map<String, int[]> memoN, int k) {
        int[] max = new int[m.length + n.length];
        for (int x = 0; x <= k; x++) {
            int[] maxMArray = memoM.getOrDefault(0 + "_" + Math.min(m.length, x), new int[]{});
            int[] maxNArray = memoN.getOrDefault(0 + "_" + Math.min(n.length, k - x), new int[]{});
            if (maxMArray.length + maxNArray.length < k) {
                continue;
            }

            int[] currentValue = new int[k];
            int indexM = 0;
            int indexN = 0;
            int currentIndex = 0;
            while (currentIndex < k) {
                int currentN = indexN < maxNArray.length ? maxNArray[indexN] : -1;
                int currentM = indexM < maxMArray.length ? maxMArray[indexM] : -1;
                if (currentM > currentN || (currentM == currentN && compare(maxMArray, indexM, maxNArray, indexN) > 0)) {
                    currentValue[currentIndex] = currentM;
                    indexM++;
                } else {
                    currentValue[currentIndex] = currentN;
                    indexN++;
                }

                currentIndex++;
            }

            if (compare(currentValue, 0, max, 0) > 0) {
                max = currentValue;
            }
        }

        return max;
    }

    // not so naive...
    private static int[] computeMaxNumberNotSoNaive(int[] m, int[] n, int k) {
        Map<Integer, int[]> maxByLengthM = new HashMap<>();
        computeMaxRecursively(m, 0, 0, k, 0, maxByLengthM);


        Map<Integer, int[]> maxByLengthN = new HashMap<>();
        computeMaxRecursively(n, 0, 0, k, 0, maxByLengthN);

        return computeMax(m, n, maxByLengthM, maxByLengthN, k);
    }

    private static int[] computeMax(int[] m, int[] n, Map<Integer, int[]> maxByLengthM, Map<Integer, int[]> maxByLengthN, int k) {
        long max = Long.MIN_VALUE;
        for (int x = 0; x <= k; x++) {
            int[] maxMArray = maxByLengthM.get(Math.min(m.length, x));
            int[] maxNArray = maxByLengthN.get(Math.min(n.length, k - x));
            if (maxMArray.length + maxNArray.length < k) {
                continue;
            }

            long currentValue = 0;
            int indexM = 0;
            int indexN = 0;
            int currentIndex = 0;
            while (currentIndex < k) {
                int currentN = indexN < maxNArray.length ? maxNArray[indexN] : -1;
                int currentM = indexM < maxMArray.length ? maxMArray[indexM] : -1;
                if (currentM > currentN || (currentM == currentN && isAGreater(maxMArray, indexM, maxNArray, indexN))) {
                    currentValue = (currentValue * 10) + currentM;
                    indexM++;
                } else {
                    currentValue = (currentValue * 10) + currentN;
                    indexN++;
                }

                currentIndex++;
            }

            max = Math.max(max, currentValue);
        }

        return convert(max);
    }

    private static boolean isAGreater(int[] a, int indexA, int[] b, int indexB) {
        while (indexA < a.length && indexB < b.length) {
            if (a[indexA] > b[indexB]) {
                return true;
            }

            indexA++;
            indexB++;
        }

        return false;
    }

    // @TODO:aorduno -- memo to come!
    private static long computeMaxRecursively(int[] nums, int currentIndex, long currentValue, int k, int length, Map<Integer, int[]> maxByLength) {
        updateMaxByLength(length, currentValue, maxByLength);
        if (currentIndex == nums.length || k == 0) {
            return currentValue;
        }
        long maxWith = computeMaxRecursively(nums, currentIndex + 1, add(currentValue, nums, currentIndex), k - 1, length + 1, maxByLength);
        long maxWithout = computeMaxRecursively(nums, currentIndex + 1, currentValue, k, length, maxByLength);
        return Math.max(maxWith, maxWithout);
    }

    private static void updateMaxByLength(int currentLength, long currentValue, Map<Integer, int[]> maxByLength) {
        int[] maxNumberByLength = maxByLength.getOrDefault(currentLength, new int[]{-1});
        maxByLength.put(currentLength, convert(
                Math.max(convert(maxNumberByLength), currentValue))
        );
    }

    private static long convert(int[] maxNumberByLength) {
        long current = 0;
        for (int aMaxNumberByLength : maxNumberByLength) {
            current = (current * 10) + aMaxNumberByLength;
        }
        return current;
    }

    private static int[] convert(long maxNumber) {
        int length = getLength(maxNumber) - 1;
        int[] num = new int[length + 1];
        int exponent = 10;
        while (maxNumber > 0) {
            num[length] = Math.toIntExact(maxNumber % exponent);
            maxNumber /= exponent;
            length--;
        }
        return num;
    }

    private static long add(long currentValue, int[] nums, int index) {
        return (currentValue * 10) + nums[index];
    }

    private static int getLength(long number) {
        int length = 0;
        while (number > 0) {
            number /= 10;
            length++;
        }

        return length;
    }

    // super naive -- not good enough since int overflows... BigInteger should suffice though...
    private static int computeMaxRecursivelyNaive(int[] m, int indexM, int[] n, int indexN, int k, int currentValue) {
        if (k == 0) {
            return currentValue;
        }

        int maxM = currentValue;
        if (indexM < m.length) {
            maxM = Math.max(
                    computeMaxRecursivelyNaive(m, indexM + 1, n, indexN, k - 1, Math.toIntExact(add(currentValue, m, indexM))),
                    computeMaxRecursivelyNaive(m, indexM + 1, n, indexN, k, currentValue)
            );
        }

        int maxN = currentValue;
        if (indexN < n.length) {
            maxN = Math.max(
                    computeMaxRecursivelyNaive(m, indexM, n, indexN + 1, k - 1, Math.toIntExact(add(currentValue, n, indexN))),
                    computeMaxRecursivelyNaive(m, indexM, n, indexN + 1, k, currentValue)
            );
        }

        return Math.max(maxM, maxN);
    }
}
