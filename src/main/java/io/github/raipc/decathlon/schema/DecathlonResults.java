package io.github.raipc.decathlon.schema;

import java.util.List;

public class DecathlonResults {
    private final String athlete;
    private final List<PerformanceRecord<?>> performanceRecords;

    public DecathlonResults(String athlete, List<PerformanceRecord<?>> results) {
        this.athlete = athlete;
        this.performanceRecords = results;
    }

    public String getAthlete() {
        return athlete;
    }

    public List<PerformanceRecord<?>> getPerformanceRecords() {
        return performanceRecords;
    }

    @Override
    public String toString() {
        return "DecathlonResults{" +
                "athlete='" + athlete + '\'' +
                ", performanceRecords=" + performanceRecords +
                '}';
    }
}
