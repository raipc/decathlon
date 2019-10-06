package io.github.raipc.decathlon.calculation;

import java.util.Arrays;
import java.util.Collection;

import io.github.raipc.decathlon.schema.Competition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static io.github.raipc.decathlon.schema.Competition.Field.*;
import static io.github.raipc.decathlon.schema.Competition.Track.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

class CoefficientsTest {
	private final CoefficientsProvider coefficientsProvider = new ConstantCoefficientsProvider();

	private static Collection<Arguments> provideTestData() {
		return Arrays.asList(
			Arguments.of(RUNNING_100M, 1000, 10.395),
			Arguments.of(RUNNING_100M, 900, 10.827),
			Arguments.of(RUNNING_100M, 800, 11.278),
			Arguments.of(RUNNING_100M, 700, 11.756),
			Arguments.of(LONG_JUMP, 1000, 776),
			Arguments.of(LONG_JUMP, 900, 736),
			Arguments.of(LONG_JUMP, 800, 694),
			Arguments.of(LONG_JUMP, 700, 651),
			Arguments.of(SHOT_PUT, 1000, 18.4),
			Arguments.of(SHOT_PUT, 900, 16.79),
			Arguments.of(SHOT_PUT, 800, 15.16),
			Arguments.of(SHOT_PUT, 700, 13.53),
			Arguments.of(HIGH_JUMP, 1000, 220),
			Arguments.of(HIGH_JUMP, 900, 210),
			Arguments.of(HIGH_JUMP, 800, 199),
			Arguments.of(HIGH_JUMP, 700, 188),
			Arguments.of(RUNNING_400M, 1000, 46.17),
			Arguments.of(RUNNING_400M, 900, 48.19),
			Arguments.of(RUNNING_400M, 800, 50.32),
			Arguments.of(RUNNING_400M, 700, 52.58),
			Arguments.of(HURDLES_110M, 1000, 13.8),
			Arguments.of(HURDLES_110M, 900, 14.59),
			Arguments.of(HURDLES_110M, 800, 15.419),
			Arguments.of(HURDLES_110M, 700, 16.29),
			Arguments.of(DISCUS_THROW, 1000, 56.17),
			Arguments.of(DISCUS_THROW, 900, 51.4),
			Arguments.of(DISCUS_THROW, 800, 46.59),
			Arguments.of(DISCUS_THROW, 700, 41.72),
			Arguments.of(POLE_VAULT, 1000, 528),
			Arguments.of(POLE_VAULT, 900, 496),
			Arguments.of(POLE_VAULT, 800, 463),
			Arguments.of(POLE_VAULT, 700, 429),
			Arguments.of(JAVELIN_THROW, 1000, 77.19),
			Arguments.of(JAVELIN_THROW, 900, 70.67),
			Arguments.of(JAVELIN_THROW, 800, 64.09),
			Arguments.of(JAVELIN_THROW, 700, 57.45),
			Arguments.of(RUNNING_1500M, 1000, 3 * 60 + 53.79),
			Arguments.of(RUNNING_1500M, 900, 4 * 60 + 7.42),
			Arguments.of(RUNNING_1500M, 800, 4 * 60 + 21.77),
			Arguments.of(RUNNING_1500M, 700, 4 * 60 + 36.96)
		);
	}

	@ParameterizedTest
	@MethodSource("provideTestData")
	public void testTrackScoreCalculation(Competition<?> competition, int expectedScore,  double performance) {
		final Coefficients coefficients = coefficientsProvider.getCoefficients(competition);
		final int score = coefficients.calculateScore(performance, competition.getEventType());
		assertThat((double)score, is(closeTo(expectedScore, 10.0))); // error is quite high in Wikipedia article
	}
}