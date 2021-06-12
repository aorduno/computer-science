package LeetCode.medium;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class KeysAndRooms {
    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(Arrays.asList());

        testCase(rooms);

        // [[1,3],[3,0,1],[2],[0]]
        rooms.clear();
        rooms.add(Arrays.asList(1, 3));
        rooms.add(Arrays.asList(3, 0, 1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));
        testCase(rooms);

        // [[1,3],[3,0,1],[2],[2]]
        rooms.clear();
        rooms.add(Arrays.asList(1, 3));
        rooms.add(Arrays.asList(3, 0, 1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(2));
        testCase(rooms);

        // [[1,1],[]]
        rooms.clear();
        rooms.add(Arrays.asList(1, 1));
        rooms.add(Arrays.asList());
        testCase(rooms);
    }

    private static void testCase(List<List<Integer>> rooms) {
        LogUtils.logMessage("[[KeysAndRooms]] Checking if all rooms given can be visited!");
        print(rooms);

        boolean canVisitAll = checkCanVisit(rooms);
        LogUtils.logMessage("CanVisit: " + canVisitAll);
    }

    private static boolean checkCanVisit(List<List<Integer>> rooms) {
        if (rooms.isEmpty()) {
            return true;
        }

        Stack<Integer> toVisit = new Stack<>();
        toVisit.add(0);

        boolean[] added = new boolean[rooms.size()];
        int canVisit = 0;
        while (!toVisit.isEmpty()) {
            // visit
            int currentRoom = toVisit.pop();
            added[currentRoom] = true;
            canVisit++;

            for (Integer room : rooms.get(currentRoom)) {
                if (added[room]) {
                    continue;
                }

                toVisit.add(room);
                added[room] = true;
            }
        }
        return canVisit == rooms.size();
    }

    private static void print(List<List<Integer>> rooms) {
        int x = 0;
        for (List<Integer> room : rooms) {
            LogUtils.logMessage("Room " + x);
            for (int key : room) {
                LogUtils.logMessage("key to -> " + key);
            }
            x++;
        }
    }
}
