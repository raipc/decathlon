package io.github.raipc.decathlon.schema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import io.github.raipc.decathlon.output.CompetitionXmlAdapter;

public class PerformanceRecord<T extends Unit<T>> {
	private final Competition<T> competition;
	private final String performance;
	private final double numericPerformance;
	private final T unit;

	public PerformanceRecord(Competition<T> competition, String performance, double numericPerformance, T unit) {
		this.competition = competition;
		this.performance = performance;
		this.numericPerformance = numericPerformance;
		this.unit = unit;
	}

	@XmlElement
	@XmlJavaTypeAdapter(CompetitionXmlAdapter.class)
	public Competition<T> getCompetition() {
		return competition;
	}

	@XmlElement
	public String getPerformance() {
		return performance;
	}

	public double getNumericPerformance() {
		return numericPerformance;
	}

	public T getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return competition + ": " + performance;
	}
}
