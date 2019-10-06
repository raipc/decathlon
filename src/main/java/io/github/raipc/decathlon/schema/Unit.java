package io.github.raipc.decathlon.schema;

public interface Unit<T extends Unit<T>> {
	double convert(double value, T source);
}
