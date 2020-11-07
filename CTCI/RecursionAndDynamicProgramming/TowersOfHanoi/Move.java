package CTCI.RecursionAndDynamicProgramming.TowersOfHanoi;

class Move {
    private TowerOfHanoi _from;
    private TowerOfHanoi _to;
    private Disk _disk;

    Move(Disk disk, TowerOfHanoi from, TowerOfHanoi to) {
        _disk = disk;
        _from = from;
        _to = to;
    }

    public TowerOfHanoi getFrom() {
        return _from;
    }

    public TowerOfHanoi getTo() {
        return _to;
    }

    public Disk getDisk() {
        return _disk;
    }
}
