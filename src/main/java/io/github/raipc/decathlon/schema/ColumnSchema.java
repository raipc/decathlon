package io.github.raipc.decathlon.schema;

import java.util.Objects;

public class ColumnSchema<T extends Unit<T>> {
	private final String columnName;
	private final ColumnType type;
	private final Competition<T> competition; // non-null only if type is PERFORMANCE
	private final String format; // non-null only if type is PERFORMANCE

	private ColumnSchema(String columnName, ColumnType type, Competition<T> competition, String format) {
		this.columnName = columnName;
		this.type = type;
		this.competition = competition;
		this.format = format;
	}

	public static <V extends Unit<V>> ColumnSchema<V> forPerformance(String columnName,
	                                                                 Competition<V> competition,
	                                                                 String format) {
		Objects.requireNonNull(competition, "Competition must be provided");
		Objects.requireNonNull(format, "Format must be provided");
		return new ColumnSchema<>(columnName, ColumnType.PERFORMANCE, competition, format);
	}

	public static ColumnSchema<?> forAthleteName(String columnName) {
		return new ColumnSchema<>(columnName, ColumnType.NAME, null ,null);
	}

	public static ColumnSchema<?> forSkip(String columnName) {
		return new ColumnSchema<>(columnName, ColumnType.SKIP, null, null);
	}

	public String getColumnName() {
		return columnName;
	}

	public ColumnType getType() {
		return type;
	}

	public Competition<T> getCompetition() {
		return competition;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ColumnSchema<?> that = (ColumnSchema<?>) o;
		return columnName.equals(that.columnName) &&
			type == that.type &&
			competition.equals(that.competition) &&
			format.equals(that.format);
	}

	@Override
	public int hashCode() {
		return Objects.hash(columnName, type, competition, format);
	}
}
