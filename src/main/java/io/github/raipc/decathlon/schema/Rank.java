package io.github.raipc.decathlon.schema;

import java.util.Objects;

public class Rank {
    private final int highest;
    private final int lowest;

    public Rank(int highest, int lowest) {
        this.highest = highest;
        this.lowest = lowest;
    }

    public Rank(int rank) {
        this(rank, rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return highest == rank.highest &&
                lowest == rank.lowest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(highest, lowest);
    }

    @Override
    public String toString() {
        return highest == lowest ? "" + highest : highest + "-" + lowest;
    }
}
