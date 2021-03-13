package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CreateMaxNumber {
    public static void main(String[] args) {
        testCase(new int[]{6, 3, 9, 0, 5, 6}, new int[]{2, 2, 5, 2, 1, 4, 4, 5, 7, 8, 9, 3, 1, 6, 9, 7, 0}, 23);
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
        testCase(new int[]{8, 9, 7, 3, 5, 9, 1, 0, 8, 5, 3, 0, 9, 2, 7, 4, 8, 9, 8, 1, 0, 2, 0, 2, 7, 2, 3, 5, 4, 7, 4, 1, 4, 0, 1, 4, 2, 1, 3, 1, 5, 3, 9, 3, 9, 0, 1, 7, 0, 6, 1, 8, 5, 6, 6, 5, 0, 4, 7, 2, 9, 2, 2, 7, 6, 2, 9, 2, 3, 5, 7, 4, 7, 0, 1, 8, 3, 6, 6, 3, 0, 8, 5, 3, 0, 3, 7, 3, 0, 9, 8, 5, 1, 9, 5, 0, 7, 9, 6, 8, 5, 1, 9, 6, 5, 8, 2, 3, 7, 1, 0, 1, 4, 3, 4, 4, 2, 4, 0, 8, 4, 6, 5, 5, 7, 6, 9, 0, 8, 4, 6, 1, 6, 7, 2, 0, 1, 1, 8, 2, 6, 4, 0, 5, 5, 2, 6, 1, 6, 4, 7, 1, 7, 2, 2, 9, 8, 9, 1, 0, 5, 5, 9, 7, 7, 8, 8, 3, 3, 8, 9, 3, 7, 5, 3, 6, 1, 0, 1, 0, 9, 3, 7, 8, 4, 0, 3, 5, 8, 1, 0, 5, 7, 2, 8, 4, 9, 5, 6, 8, 1, 1, 8, 7, 3, 2, 3, 4, 8, 7, 9, 9, 7, 8, 5, 2, 2, 7, 1, 9, 1, 5, 5, 1, 3, 5, 9, 0, 5, 2, 9, 4, 2, 8, 7, 3, 9, 4, 7, 4, 8, 7, 5, 0, 9, 9, 7, 9, 3, 8, 0, 9, 5, 3, 0, 0, 3, 0, 4, 9, 0, 9, 1, 6, 0, 2, 0, 5, 2, 2, 6, 0, 0, 9, 6, 3, 4, 1, 2, 0, 8, 3, 6, 6, 9, 0, 2, 1, 6, 9, 2, 4, 9, 0, 8, 3, 9, 0, 5, 4, 5, 4, 6, 1, 2, 5, 2, 2, 1, 7, 3, 8, 1, 1, 6, 8, 8, 1, 8, 5, 6, 1, 3, 0, 1, 3, 5, 6, 5, 0, 6, 4, 2, 8, 6, 0, 3, 7, 9, 5, 5, 9, 8, 0, 4, 8, 6, 0, 8, 6, 6, 1, 6, 2, 7, 1, 0, 2, 2, 4, 0, 0, 0, 4, 6, 5, 5, 4, 0, 1, 5, 8, 3, 2, 0, 9, 7, 6, 2, 6, 9, 9, 9, 7, 1, 4, 6, 2, 8, 2, 5, 3, 4, 5, 2, 4, 4, 4, 7, 2, 2, 5, 3, 2, 8, 2, 2, 4, 9, 8, 0, 9, 8, 7, 6, 2, 6, 7, 5, 4, 7, 5, 1, 0, 5, 7, 8, 7, 7, 8, 9, 7, 0, 3, 7, 7, 4, 7, 2, 0, 4, 1, 1, 9, 1, 7, 5, 0, 5, 6, 6, 1, 0, 6, 9, 4, 2, 8, 0, 5, 1, 9, 8, 4, 0, 3, 1, 2, 4, 2, 1, 8, 9, 5, 9, 6, 5, 3, 1, 8, 9, 0, 9, 8, 3, 0, 9, 4, 1, 1, 6, 0, 5, 9, 0, 8, 3, 7, 8, 5}, new int[]{7, 8, 4, 1, 9, 4, 2, 6, 5, 2, 1, 2, 8, 9, 3, 9, 9, 5, 4, 4, 2, 9, 2, 0, 5, 9, 4, 2, 1, 7, 2, 5, 1, 2, 0, 0, 5, 3, 1, 1, 7, 2, 3, 3, 2, 8, 2, 0, 1, 4, 5, 1, 0, 0, 7, 7, 9, 6, 3, 8, 0, 1, 5, 8, 3, 2, 3, 6, 4, 2, 6, 3, 6, 7, 6, 6, 9, 5, 4, 3, 2, 7, 6, 3, 1, 8, 7, 5, 7, 8, 1, 6, 0, 7, 3, 0, 4, 4, 4, 9, 6, 3, 1, 0, 3, 7, 3, 6, 1, 0, 0, 2, 5, 7, 2, 9, 6, 6, 2, 6, 8, 1, 9, 7, 8, 8, 9, 5, 1, 1, 4, 2, 0, 1, 3, 6, 7, 8, 7, 0, 5, 6, 0, 1, 7, 9, 6, 4, 8, 6, 7, 0, 2, 3, 2, 7, 6, 0, 5, 0, 9, 0, 3, 3, 8, 5, 0, 9, 3, 8, 0, 1, 3, 1, 8, 1, 8, 1, 1, 7, 5, 7, 4, 1, 0, 0, 0, 8, 9, 5, 7, 8, 9, 2, 8, 3, 0, 3, 4, 9, 8, 1, 7, 2, 3, 8, 3, 5, 3, 1, 4, 7, 7, 5, 4, 9, 2, 6, 2, 6, 4, 0, 0, 2, 8, 3, 3, 0, 9, 1, 6, 8, 3, 1, 7, 0, 7, 1, 5, 8, 3, 2, 5, 1, 1, 0, 3, 1, 4, 6, 3, 6, 2, 8, 6, 7, 2, 9, 5, 9, 1, 6, 0, 5, 4, 8, 6, 6, 9, 4, 0, 5, 8, 7, 0, 8, 9, 7, 3, 9, 0, 1, 0, 6, 2, 7, 3, 3, 2, 3, 3, 6, 3, 0, 8, 0, 0, 5, 2, 1, 0, 7, 5, 0, 3, 2, 6, 0, 5, 4, 9, 6, 7, 1, 0, 4, 0, 9, 6, 8, 3, 1, 2, 5, 0, 1, 0, 6, 8, 6, 6, 8, 8, 2, 4, 5, 0, 0, 8, 0, 5, 6, 2, 2, 5, 6, 3, 7, 7, 8, 4, 8, 4, 8, 9, 1, 6, 8, 9, 9, 0, 4, 0, 5, 5, 4, 9, 6, 7, 7, 9, 0, 5, 0, 9, 2, 5, 2, 9, 8, 9, 7, 6, 8, 6, 9, 2, 9, 1, 6, 0, 2, 7, 4, 4, 5, 3, 4, 5, 5, 5, 0, 8, 1, 3, 8, 3, 0, 8, 5, 7, 6, 8, 7, 8, 9, 7, 0, 8, 4, 0, 7, 0, 9, 5, 8, 2, 0, 8, 7, 0, 3, 1, 8, 1, 7, 1, 6, 9, 7, 9, 7, 2, 6, 3, 0, 5, 3, 6, 0, 5, 9, 3, 9, 1, 1, 0, 0, 8, 1, 4, 3, 0, 4, 3, 7, 7, 7, 4, 6, 4, 0, 0, 5, 7, 3, 2, 8, 5, 1, 4, 5, 8, 5, 6, 7, 5, 7, 3, 3, 9, 6, 8, 1, 5, 1, 1, 1, 0, 3}, 500);
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

        int[] max = computeMaxNumberGreedy(m, n, k);
        LogUtils.logMessage("Result:");
        ArrayUtils.print(max);
    }

    private static int[] computeMaxNumberGreedy(int[] m, int[] n, int k) {
        int diff = (m.length + n.length) - k;
        int[] max = null;
        for (int x = 0; x <= diff; x++) {
            LinkedList<Integer> maxM = computeMaxWithLength(m, x);
            LinkedList<Integer> maxN = computeMaxWithLength(n, diff - x);
            int[] currentMax = computeMax(maxM, maxN, k);
            max = max != null ? compare(max, currentMax) : currentMax;
        }

        return max;
    }

    // at this point these are the same length
    private static int[] compare(int[] max, int[] currentMax) {
        for (int x = 0; x < max.length; x++) {
            if (max[x] != currentMax[x]) {
                return max[x] > currentMax[x] ? max : currentMax;
            }
        }

        return max;
    }

    private static LinkedList<Integer> computeMaxWithLength(int[] nums, int toIgnore) {
        LinkedList<Integer> max = new LinkedList<>();
        for (int x = 0; x < nums.length; x++) {
            int current = nums[x];
            while (toIgnore > 0 && !max.isEmpty() && max.getLast() < current) {
                max.removeLast();
                toIgnore--;
            }

            max.addLast(current);
        }
        return max;
    }

    private static int[] computeMax(LinkedList<Integer> maxM, LinkedList<Integer> maxN, int k) {
        int index = 0;
        int[] max = new int[k];
        while (index < k && !maxM.isEmpty() && !maxN.isEmpty()) {
            int currentM = maxM.getFirst();
            int currentN = maxN.getFirst();
            if (currentM > currentN) {
                max[index] = maxM.removeFirst();
            } else if (currentN > currentM) {
                max[index] = maxN.removeFirst();
            } else {
                if (compare(maxM, maxN) >= 0) {
                    max[index] = maxM.removeFirst();
                } else {
                    max[index] = maxN.removeFirst();
                }
            }
            index++;
        }

        while (index < k && !maxM.isEmpty()) {
            max[index] = maxM.removeFirst();
            index++;
        }

        while (index < k && !maxN.isEmpty()) {
            max[index] = maxN.removeFirst();
            index++;
        }

        return max;
    }

    private static int compare(LinkedList<Integer> maxM, LinkedList<Integer> maxN) {
        int x = 1;
        int y = 1;
        int currentM = maxM.getFirst();
        int currentN = maxN.getFirst();
        while (x < maxM.size() && y < maxN.size() && currentM == currentN) {
            currentM = maxM.get(x);
            currentN = maxN.get(x);
            if (currentM > currentN) {
                return 1;
            }
            if (currentN > currentM) {
                return -1;
            }
            x++;
            y++;
        }

        if (x < maxM.size() || y < maxN.size()) {
            int leftA = maxM.size() - x;
            int leftB = maxN.size() - y;
            return leftA - leftB > 0 ? 1 : -1;
        }

        return currentM - currentN;
    }

    // not accepted!
    private static int[] computeMaxNumberDPBetter(int[] m, int[] n, int k) {
        String[] memoM = computeMaxByLengthDpBetter(m, k);
        String[] memoN = computeMaxByLengthDpBetter(n, k);
        return computeMaxDpBetter(m, n, memoM.length == 0 ? new String[]{""} : memoM, memoN.length == 0 ? new String[]{""} : memoN, k);
    }

    private static int[] computeMaxDpBetter(int[] m, int[] n, String[] memoM, String[] memoN, int k) {
        String max = "";
        for (int x = 0; x <= k; x++) {
            String maxMArray = memoM[Math.min(x, m.length)];
            String maxNArray = memoN[Math.min(k - x, n.length)];
            if (maxMArray.length() + maxNArray.length() < k) {
                continue;
            }

            StringBuilder currentValue = new StringBuilder();
            int indexM = 0;
            int indexN = 0;
            while (currentValue.length() < k) {
                int currentN = indexN < maxNArray.length() ? maxNArray.charAt(indexN) - '0' : -1;
                int currentM = indexM < maxMArray.length() ? maxMArray.charAt(indexM) - '0' : -1;
                if (currentM > currentN || (currentM == currentN && maxMArray.substring(indexM).compareTo(maxNArray.substring(indexN))/*compare(maxMArray, indexM, maxNArray, indexN) */ > 0)) {
                    currentValue.append(currentM);
                    indexM++;
                } else {
                    currentValue.append(currentN);
                    indexN++;
                }
            }

            if (currentValue.toString().compareTo(max) > 0) {
                max = currentValue.toString();
            }
        }

        return convert(max);
    }

    private static int[] convert(String max) {
        int[] converted = new int[max.length()];
        for (int x = 0; x < max.length(); x++) {
            converted[x] = max.charAt(x) - '0';
        }

        return converted;
    }

    private static String getOrDefault(int k, int index, String[][] memo) {
        return index >= memo[k].length ? "" : memo[k][index];
    }

    private static int compare(String a, int indexA, String b, int indexB) {
        int aLength = a.length();
        int bLength = b.length();
        while (indexA < aLength && indexB < bLength) {
            int currentA = a.charAt(indexA) - '0';
            int currentB = b.charAt(indexB) - '0';
            if (currentA != currentB) {
                return currentA - currentB > 0 ? 1 : -1;
            }

            indexA++;
            indexB++;
        }

        if (indexA < aLength || indexB < bLength) {
            int leftA = aLength - indexA;
            int leftB = bLength - indexB;
            return leftA - leftB > 0 ? 1 : -1;
        }

        return 0;
    }

    private static String[] computeMaxByLengthDpBetter(int[] nums, int k) {
        String[][] memo = new String[nums.length + 1][k + 1];

        for (int length = 0; length <= Math.min(nums.length, k); length++) {
            for (int index = nums.length - length; index >= 0; index--) {
                if (length == 0) {
                    memo[index][length] = "";
                    continue;
                }

                String previousKeyMax = getMemoValue(length, index + 1, memo);
                String previousLengthMax = getMemoValue(length - 1, index + 1, memo);
                String currentNum = nums[index] + previousLengthMax;
                memo[index][length] = currentNum.compareTo(previousKeyMax) > 0 ? currentNum : previousKeyMax;
            }
        }


        return memo[0];
    }

    private static String getMemoValue(int length, int index, String[][] memo) {
        return index == memo.length || memo[index][length] == null ? "" : memo[index][length];
    }

    // DP not accepted!
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

    // not so naive... not accepted!
    private static int[] computeMaxNumberNotSoNaive(int[] m, int[] n, int k) {
        Map<Integer, int[]> maxByLengthM = new HashMap<>();
        computeMaxRecursively(m, 0, 0, k, 0, maxByLengthM);


        Map<Integer, int[]> maxByLengthN = new HashMap<>();
        computeMaxRecursively(n, 0, 0, k, 0, maxByLengthN);

        return computeMax(m, n, maxByLengthM, maxByLengthN, k);
    }

    private static int[] computeMax(int[] m, int[] n, Map<Integer, int[]> maxByLengthM, Map<Integer, int[]>
            maxByLengthN, int k) {
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
    private static long computeMaxRecursively(int[] nums, int currentIndex, long currentValue, int k,
                                              int length, Map<Integer, int[]> maxByLength) {
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

    // super naive -- not good enough since int overflows... BigInteger should suffice though... not accepted!
    private static int computeMaxRecursivelyNaive(int[] m, int indexM, int[] n, int indexN, int k,
                                                  int currentValue) {
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
