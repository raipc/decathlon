package io.github.raipc.decathlon.calculation;

import io.github.raipc.decathlon.schema.PerformanceRecord;

import java.util.List;

public interface ScoreCalculator {
    int calculateScore(List<PerformanceRecord<?>> records);
}
