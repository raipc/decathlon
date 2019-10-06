package io.github.raipc.decathlon.calculation;

import io.github.raipc.decathlon.schema.DecathlonAthletePerformanceWithRank;
import io.github.raipc.decathlon.schema.DecathlonRanking;
import io.github.raipc.decathlon.schema.DecathlonResults;
import io.github.raipc.decathlon.schema.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DecathlonAthletesRanksCalculator {
    private final ScoreCalculator scoreCalculator;

    public DecathlonAthletesRanksCalculator(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    public DecathlonRanking assignRanks(List<DecathlonResults> results) {
        if (results.isEmpty()) {
            return new DecathlonRanking(Collections.emptyList());
        }
        final List<ResultsAndScore> resultsAndScores = results.stream()
                .map(this::withScore)
                .sorted()
                .collect(Collectors.toList());
        final int size = resultsAndScores.size();
        final List<DecathlonAthletePerformanceWithRank> result = new ArrayList<>(size);
        int withSameRankStart = 0;
        int previousScore = resultsAndScores.get(0).score;
        for (int i = 1; i < size; i++) {
            final int currentScore = resultsAndScores.get(i).score;
            if (currentScore != previousScore) {
                final Rank rank = new Rank(withSameRankStart + 1, i);
                addAthletesWithRank(rank, resultsAndScores.subList(withSameRankStart, i), result);
                previousScore = currentScore;
                withSameRankStart = i;
            }
        }
        final Rank rank = new Rank(withSameRankStart + 1, size);
        addAthletesWithRank(rank, resultsAndScores.subList(withSameRankStart, size), result);
        return new DecathlonRanking(result);
    }

    private static void addAthletesWithRank(Rank rank, List<ResultsAndScore> records,
                                            List<DecathlonAthletePerformanceWithRank> accumulator) {
        for (ResultsAndScore record : records) {
            accumulator.add(new DecathlonAthletePerformanceWithRank(rank, record.results.getAthlete(),
                    record.results.getPerformanceRecords(), record.score));
        }
    }

    private ResultsAndScore withScore(DecathlonResults results) {
        final int totalScore = scoreCalculator.calculateScore(results.getPerformanceRecords());
        return new ResultsAndScore(results, totalScore);
    }

    private static final class ResultsAndScore implements Comparable<ResultsAndScore> {
        private final DecathlonResults results;
        private final int score;

        private ResultsAndScore(DecathlonResults results, int score) {
            this.results = results;
            this.score = score;
        }

        @Override
        public int compareTo(ResultsAndScore o) {
            return -Integer.compare(score, o.score); // reverse order
        }
    }
}
