package io.github.raipc.decathlon.calculation;

import io.github.raipc.decathlon.schema.EventType;

public class Coefficients {
	private final double a;
	private final double b;
	private final double c;

	Coefficients(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int calculateScore(double performance, EventType eventType) {
		double delta = eventType == EventType.TRACK ? b - performance : performance - b;
		return (int) (a * Math.pow(delta, c));
	}
}

