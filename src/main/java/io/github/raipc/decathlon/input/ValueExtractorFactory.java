package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.DistanceUnit;
import io.github.raipc.decathlon.schema.TrackTimeUnit;

public class ValueExtractorFactory {
    public static ValueExtractor<TrackTimeUnit> createTimeExtractor(String format) {
        return new TimeValueExtractor(new TimeParser(format));
    }

    public static ValueExtractor<DistanceUnit> createDistanceExtractor(String format) {
        final DistanceParser parser = DistancePattern.parse(format).createParser();
        return new DistanceValueExtractor(parser);
    }
}
