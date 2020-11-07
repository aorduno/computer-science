package CTCI.StacksAndQueues;

import DataStructures.StackG;

import java.util.List;

public class SetOfStacks<T> {
    StackG<T> _currentStack;
    List<StackG> _stacks;

    int _maxCapacity;
    int _currentSize;

//    SetOfStacks(int maxCapacity) {
//        _maxCapacity = maxCapacity;
//        _currentStack = new StackG<>();
//    }
//
//    T push(T data) {
//        return _currentStack.push(data);
//    }
}
