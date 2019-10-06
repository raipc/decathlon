package io.github.raipc.decathlon.calculation;

import java.util.List;

import io.github.raipc.decathlon.schema.Competition;
import io.github.raipc.decathlon.schema.PerformanceRecord;
import io.github.raipc.decathlon.schema.Unit;

public class ScoreCalculator {
	private final CoefficientsProvider scoresProvider;

	public ScoreCalculator(CoefficientsProvider scoresProvider) {
		this.scoresProvider = scoresProvider;
	}

	public int calculateScore(List<PerformanceRecord<?>> records) {
		return records.stream()
			.mapToInt(this::calculateScoreForOneDiscipline)
			.sum();
	}

	private <T extends Unit<T>> int calculateScoreForOneDiscipline(PerformanceRecord<T> record) {
		final Competition<T> competition = record.getCompetition();
		final Coefficients coefficients = scoresProvider.getCoefficients(competition);
		final double rawValue = record.getNumericPerformance();
		final double normalizedValue = competition.getRequiredUnit().convert(rawValue, record.getUnit());
		return coefficients.calculateScore(normalizedValue, competition.getEventType());
	}
}
