package CTCI.RecursionAndDynamicProgramming.TowersOfHanoi;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class TowersOfHanoi {
    private TowerOfHanoi t1 = new TowerOfHanoi("first", 1);
    private TowerOfHanoi t2 = new TowerOfHanoi("second", 2);
    private TowerOfHanoi t3 = new TowerOfHanoi("third", 3);


    private TowersOfHanoi(int disks) {
        configureDisks(disks);
    }

    private void configureDisks(int disks) {
        // create
        while (disks > 0) {
            Disk disk = new Disk(disks);
            t1.getTower().add(disk);
            disks--;
        }
    }

    public static void main(String[] args) {
        int c = 1;
        while (c < 10) {
            testCase(c);
            c++;
        }
    }

    private static void testCase(int disks) {
        LogUtils.logMessage("[[TowersOfHanoi]] Figuring out towers of hanoi for number of disks: " + disks);

        TowersOfHanoi towersOfHanoi = createBoard(disks);
        resolve(towersOfHanoi);
    }

    private static void resolve(TowersOfHanoi towersOfHanoi) {
        // move everything for first tower to third tower
        TowerOfHanoi t1 = towersOfHanoi.getT1();
        List<Move> moveList = new ArrayList<>();
        t1.moveDisks(t1.getTower().size(), towersOfHanoi.getT3(), towersOfHanoi.getT2(), moveList);
        printMoves(moveList);
    }

    private static void printMoves(List<Move> moveList) {
        LogUtils.logMessage("Here are the moves to resolve it:");
        for (Move move : moveList) {
            LogUtils.logMessage("Move disk " + move.getDisk().getSize() + " from T" + move.getFrom().getNumber() + " to T" + move.getTo().getNumber());
        }
    }

    private static TowersOfHanoi createBoard(int disks) {
        return new TowersOfHanoi(disks);
    }

    private TowerOfHanoi getT1() {
        return t1;
    }

    private TowerOfHanoi getT2() {
        return t2;
    }

    private TowerOfHanoi getT3() {
        return t3;
    }
}
