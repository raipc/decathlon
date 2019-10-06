package io.github.raipc.decathlon.calculation;

import io.github.raipc.decathlon.schema.Competition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Coefficients provider with values taken from Wikipedia article.
 */
public class ConstantCoefficientsProvider implements CoefficientsProvider {
    private final Map<Competition<?>, Coefficients> scores;

    public ConstantCoefficientsProvider() {
        scores = prepareScores();
        final List<Competition<?>> values = Competition.values();
        if (scores.size() != values.size()) {
            final String missingCompetitions = values.stream()
                    .filter(competition -> scores.get(competition) == null) // in case key exists but it is null
                    .map(Competition::name)
                    .collect(Collectors.joining(", "));
            throw new IllegalStateException(String.format("Missing score for competitions: %s", missingCompetitions));
        }
    }

    /**
     * Initialize coefficients with data taken from https://en.wikipedia.org/wiki/Decathlon
     *
     * @return map with coefficients
     */
    private static Map<Competition<?>, Coefficients> prepareScores() {
        final Map<Competition<?>, Coefficients> result = new HashMap<>();
        result.put(Competition.Track.RUNNING_100M, new Coefficients(25.4347, 18, 1.81));
        result.put(Competition.Field.LONG_JUMP, new Coefficients(0.14354, 220, 1.4));
        result.put(Competition.Field.SHOT_PUT, new Coefficients(51.39, 1.5, 1.05));
        result.put(Competition.Field.HIGH_JUMP, new Coefficients(0.8465, 75, 1.42));
        result.put(Competition.Track.RUNNING_400M, new Coefficients(1.53775, 82, 1.81));
        result.put(Competition.Track.HURDLES_110M, new Coefficients(5.74352, 28.5, 1.92));
        result.put(Competition.Field.DISCUS_THROW, new Coefficients(12.91, 4, 1.1));
        result.put(Competition.Field.POLE_VAULT, new Coefficients(0.2797, 100, 1.35));
        result.put(Competition.Field.JAVELIN_THROW, new Coefficients(10.14, 7, 1.08));
        result.put(Competition.Track.RUNNING_1500M, new Coefficients(0.03768, 480, 1.85));
        return result;
    }

    @Override
    public Coefficients getCoefficients(Competition<?> competition) {
        return scores.get(competition);
    }
}
