package CTCI.SortingAndSearching;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Listy {
    private Map<Integer, Integer> _indexNumberMap = new HashMap<>();
    private List<Integer> _integerList = new ArrayList<>();

    public void add(int number) {
        int size = _integerList.size();
        _integerList.add(number);
        _indexNumberMap.put(size, number);
    }

    public int get(int index) {
        Integer integer = _indexNumberMap.get(index);
        return integer == null ? -1 : integer;
    }

    public int search(int target) {
        int left = 0;
        int right = getRightIndex(target);
        return doSearch(target, left, right);
    }

    private int doSearch(int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = left + ((right - left) / 2);
        int current = get(middle);
        if (current == target) {
            return middle;
        } else if (current > target || current == -1) {
            return doSearch(target, left, middle - 1);
        } else {
            return doSearch(target, middle + 1, right);
        }
    }

    private int getRightIndex(int target) {
        int index = 1; // start at first position
        int current = 0;
        while (current != -1 && current < target) {
            current = get(index);
            index *= 2;
        }

        return index;
    }

    public static void main(String[] args) {
        Listy listy = new Listy();
        listy.add(5);
        listy.add(20);
        listy.add(1000);
        listy.add(9999);
        listy.add(55555);
        listy.add(55556);
        listy.add(908776);
        listy.add(1000000);

        testCase(listy, 9999);
        testCase(listy, 55556);
        testCase(listy, 55555);
        testCase(listy, 5);
        testCase(listy, 20);
        testCase(listy, 1000);
        testCase(listy, 908776);
        testCase(listy, 1000000);
        testCase(listy, 666);
        testCase(listy, 10000000);
        testCase(listy, 3);
    }

    private static void testCase(Listy listy, int target) {
        LogUtils.logMessage("[[Listy]] Finding " + target + " in a very cool data structure", true);
        LogUtils.logMessage("Target exists at index: " + listy.search(target), true);
    }
}
