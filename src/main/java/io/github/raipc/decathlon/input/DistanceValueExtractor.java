package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.DistanceUnit;

class DistanceValueExtractor implements ValueExtractor<DistanceUnit> {
	private final DistanceParser distanceParser;

	DistanceValueExtractor(DistanceParser distanceParser) {
		this.distanceParser = distanceParser;
	}

	@Override
	public double extractNumericValue(String value) {
		return distanceParser.parseDistance(value);
	}

	@Override
	public DistanceUnit getUnit() {
		return distanceParser.getUnit();
	}
}
