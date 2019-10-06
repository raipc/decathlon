package io.github.raipc.decathlon.schema;

import java.util.concurrent.TimeUnit;

public enum TrackTimeUnit implements Unit<TrackTimeUnit> {
    NANOSECONDS() {
        @Override
        public double convert(double value, TrackTimeUnit source) {
            switch (source) {
                case NANOSECONDS:
                    return value;
                case SECONDS:
                    return value * TimeUnit.SECONDS.toNanos(1);
                default:
                    throw TrackTimeUnit.prepareUnsupportedException(source, this);
            }
        }
    },
    SECONDS() {
        @Override
        public double convert(double value, TrackTimeUnit source) {
            switch (source) {
                case SECONDS:
                    return value;
                case NANOSECONDS:
                    return value / TimeUnit.SECONDS.toNanos(1);
                default:
                    throw TrackTimeUnit.prepareUnsupportedException(source, this);
            }
        }
    };

    private static UnsupportedOperationException prepareUnsupportedException(TrackTimeUnit from, TrackTimeUnit to) {
        final String message = String.format("Conversion from %s to %s is not supported", from, to);
        throw new UnsupportedOperationException(message);
    }
}
