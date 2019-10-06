package io.github.raipc.decathlon.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.github.raipc.decathlon.schema.ColumnType;
import io.github.raipc.decathlon.schema.DecathlonResults;
import io.github.raipc.decathlon.schema.EventType;
import io.github.raipc.decathlon.schema.PerformanceRecord;
import io.github.raipc.decathlon.schema.ColumnSchema;
import io.github.raipc.decathlon.schema.Unit;

public class IndexBasedColumnMapper implements Function<String[], DecathlonResults> {
	private final List<ColumnSchema<?>> schema;
	private final List<ValueExtractor<?>> valueExtractors;

	public IndexBasedColumnMapper(List<ColumnSchema<?>> schema) {
		this.schema = schema;
		this.valueExtractors = schema.stream()
				.map(this::createValueExtractor)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private <T extends Unit<T>> ValueExtractor<T> createValueExtractor(ColumnSchema<T> column) {
		if (column.getType() != ColumnType.PERFORMANCE) {
			return null;
		}
		if (column.getCompetition().getEventType() == EventType.TRACK) {
			return (ValueExtractor<T>) ValueExtractorFactory.createTimeExtractor(column.getFormat());
		} else if (column.getCompetition().getEventType() == EventType.FIELD) {
			return (ValueExtractor<T>) ValueExtractorFactory.createDistanceExtractor(column.getFormat());
		} else {
			throw new UnsupportedOperationException("Cannot resolve value extractor for column");
		}
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public DecathlonResults apply(String[] strings) {
		final int size = schema.size();
		if (size != strings.length) {
			throw new IllegalStateException(String.format(
				"Row doesn't satisfy the schema. Expected %d columns, but received %d columns: %s",
				size, strings.length, Arrays.asList(strings)));
		}
		String athleteName = null;
		final List<PerformanceRecord<?>> performanceRecords = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			final ColumnSchema<?> columnSchema = schema.get(i);
			final ColumnType type = columnSchema.getType();
			if (type == ColumnType.NAME) {
				athleteName = strings[i];
			} else if (type == ColumnType.PERFORMANCE) {
				final ValueExtractor valueExtractor = valueExtractors.get(i);
				final PerformanceRecord record = resolvePerformanceRecord(columnSchema, valueExtractor, strings[i]);
				performanceRecords.add(record);
			}
		}
		return new DecathlonResults(athleteName, performanceRecords);
	}

	private <T extends Unit<T>> PerformanceRecord<T> resolvePerformanceRecord(ColumnSchema<T> columnSchema,
	                                                                          ValueExtractor<T> valueExtractor,
	                                                                          String value) {
		final String trimmedValue = value.trim();
		final double performanceAsNumber = valueExtractor.extractNumericValue(trimmedValue);
		final T unit = valueExtractor.getUnit();
		return new PerformanceRecord<T>(columnSchema.getCompetition(), trimmedValue, performanceAsNumber, unit);
	}
}
