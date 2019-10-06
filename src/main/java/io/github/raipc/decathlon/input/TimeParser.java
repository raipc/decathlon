package io.github.raipc.decathlon.input;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQueries;
import java.util.Locale;

public class TimeParser {
	private final DateTimeFormatter parser;

	public TimeParser(String format) {
		this.parser = new DateTimeFormatterBuilder()
			.parseLenient()
			.appendPattern(format)
			.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
			.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
			.parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
			.toFormatter(Locale.US)
			.withResolverStyle(ResolverStyle.LENIENT);
	}

	public long parseNanos(String value) {
		return parser.parse(value)
			.query(TemporalQueries.localTime())
			.toNanoOfDay();
	}
}
