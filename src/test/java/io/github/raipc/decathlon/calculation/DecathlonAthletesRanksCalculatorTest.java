package io.github.raipc.decathlon.calculation;

import io.github.raipc.decathlon.schema.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_100M;
import static io.github.raipc.decathlon.schema.TrackTimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;

class DecathlonAthletesRanksCalculatorTest {
    @Test
    public void testScoreRankAssignation() {
        final List<PerformanceRecord<?>> firstPlaceResult = Arrays.asList(
                new PerformanceRecord<>(RUNNING_100M, "10.0", 10.0, SECONDS));
        final List<PerformanceRecord<?>> secondThirdPlaceResult = Arrays.asList(
                new PerformanceRecord<>(RUNNING_100M, "10.0", 10.5, SECONDS));
        List<DecathlonResults> results = Arrays.asList(
                new DecathlonResults("John Doe", secondThirdPlaceResult),
                new DecathlonResults("Ivan Ivanov", firstPlaceResult),
                new DecathlonResults("John Doe Jr", secondThirdPlaceResult)
        );

        final ScoreCalculator calculator = Mockito.mock(ScoreCalculator.class);
        Mockito.doReturn(9000).when(calculator).calculateScore(eq(firstPlaceResult));
        Mockito.doReturn(8500).when(calculator).calculateScore(eq(secondThirdPlaceResult));
        final DecathlonAthletesRanksCalculator ranksCalculator = new DecathlonAthletesRanksCalculator(calculator);
        final DecathlonRanking decathlonRanking = ranksCalculator.assignRanks(results);
        List<DecathlonAthletePerformanceWithRank> expected = Arrays.asList(
                new DecathlonAthletePerformanceWithRank(new Rank(1), "Ivan Ivanov", firstPlaceResult, 9000),
                new DecathlonAthletePerformanceWithRank(new Rank(2, 3), "John Doe", secondThirdPlaceResult, 8500),
                new DecathlonAthletePerformanceWithRank(new Rank(2, 3), "John Doe Jr", secondThirdPlaceResult, 8500)
        );
        assertThat(decathlonRanking.getEntries(), is(expected));
    }
}