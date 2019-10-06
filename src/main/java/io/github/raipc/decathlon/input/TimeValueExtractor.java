package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.TrackTimeUnit;

class TimeValueExtractor implements ValueExtractor<TrackTimeUnit> {
    private final TimeParser timeParser;

    TimeValueExtractor(TimeParser timeParser) {
        this.timeParser = timeParser;
    }

    @Override
    public double extractNumericValue(String value) {
        return timeParser.parseNanos(value);
    }

    @Override
    public TrackTimeUnit getUnit() {
        return TrackTimeUnit.NANOSECONDS;
    }
}
