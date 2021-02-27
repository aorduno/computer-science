package CTCI.RecursionAndDynamicProgramming.TowersOfHanoi;

import CTCI.LogUtils;

import java.util.List;
import java.util.Stack;

public class TowerOfHanoi {
    private Stack<Disk> _tower = new Stack<>();
    private String _name;
    private int _number;

    TowerOfHanoi(String name, int number) {
        _name = name;
        _number = number;
    }


    Stack<Disk> getTower() {
        return _tower;
    }

    public String getName() {
        return _name;
    }

    public int getNumber() {
        return _number;
    }

    // moving disks works as follows...
    // 1) move all disks but one from "this" tower to buffer (using target as buffer)
    // 2) move the last outstanding disk to target
    // 3) move everything that got moved to buffer to destination (using this tower as buffer)
    void moveDisks(int disksToMove, TowerOfHanoi target, TowerOfHanoi buffer, List<Move> moveList) {
        if (disksToMove == 0) {
            return;
        }

        int allDisksButOne = disksToMove - 1;
        moveDisks(allDisksButOne, buffer, target, moveList);
//        LogUtils.logMessage("Moving disk " + getTower().peek().getSize() + " from: T" + getNumber() + " to T" + target.getNumber());
        recordMove(target, moveList);
        moveTop(target);

        buffer.moveDisks(allDisksButOne, target, this, moveList);
    }

    private void recordMove(TowerOfHanoi target, List<Move> moveList) {
        moveList.add(new Move(getTower().peek(), this, target));
    }

    private void moveTop(TowerOfHanoi target) {
        Disk top = getTower().pop();
        target.add(top);
    }

    private void add(Disk disk) {
        if (!getTower().isEmpty() && (getTower().peek().getSize() < disk.getSize())) {
            LogUtils.logMessage("Invalid move found! Can not move disk " + disk.getSize() + " to T" + getName() + " since disk at top is " + getTower().peek().getSize(), true);
            return;
        }

        getTower().add(disk);
    }
}
