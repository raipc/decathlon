package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.Unit;

public interface ValueExtractor<T extends Unit<T>> {
	double extractNumericValue(String value);

	T getUnit();
}
