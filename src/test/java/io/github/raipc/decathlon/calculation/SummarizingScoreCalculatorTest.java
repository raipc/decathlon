package io.github.raipc.decathlon.calculation;

import io.github.raipc.decathlon.schema.DistanceUnit;
import io.github.raipc.decathlon.schema.PerformanceRecord;
import io.github.raipc.decathlon.schema.TrackTimeUnit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.github.raipc.decathlon.schema.Competition.Field.*;
import static io.github.raipc.decathlon.schema.Competition.Track.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ScoreCalculatorTest {
    @Test
    public void calculateKevinMeyerScore() {
        final ScoreCalculator scoreCalculator = new SummarizingScoreCalculator(new ConstantCoefficientsProvider());
        List<PerformanceRecord<?>> kevinMeyerPerformance = Arrays.asList(
                new PerformanceRecord<>(RUNNING_100M, "10.55", 10.55, TrackTimeUnit.SECONDS),
                new PerformanceRecord<>(LONG_JUMP, "7.80", 7.80, DistanceUnit.METERS),
                new PerformanceRecord<>(SHOT_PUT, "16.00", 16.00, DistanceUnit.METERS),
                new PerformanceRecord<>(HIGH_JUMP, "2.05", 2.05, DistanceUnit.METERS),
                new PerformanceRecord<>(RUNNING_400M, "48.42", 48.42, TrackTimeUnit.SECONDS),
                new PerformanceRecord<>(HURDLES_110M, "13.75", 13.75, TrackTimeUnit.SECONDS),
                new PerformanceRecord<>(DISCUS_THROW, "50.54", 50.54, DistanceUnit.METERS),
                new PerformanceRecord<>(POLE_VAULT, "5.45", 5.45, DistanceUnit.METERS),
                new PerformanceRecord<>(JAVELIN_THROW, "71.90", 71.90, DistanceUnit.METERS),
                new PerformanceRecord<>(RUNNING_1500M, "4:36.11", 60 * 4 + 36.11, TrackTimeUnit.SECONDS)
        );
        final int kevinMeyerScore = scoreCalculator.calculateScore(kevinMeyerPerformance);
        assertThat(kevinMeyerScore, is(9126)); // source: wikipedia
    }
}