package io.github.raipc.decathlon.schema;

public class Rank {
    private final int highest;
    private final int lowest;

    public Rank(int highest, int lowest) {
        this.highest = highest;
        this.lowest = lowest;
    }

    @Override
    public String toString() {
        return highest == lowest ? "" + highest : highest + "-" + lowest;
    }
}
