package io.github.raipc.decathlon.schema;

public enum DistanceUnit implements Unit<DistanceUnit> {
    METERS {
        @Override
        public double convert(double value, DistanceUnit source) {
            switch (source) {
                case METERS:
                    return value;
                case CENTIMETERS:
                    return value / 100;
                default:
                    throw DistanceUnit.prepareUnsupportedException(source, this);
            }
        }
    },
    CENTIMETERS {
        @Override
        public double convert(double value, DistanceUnit source) {
            switch (source) {
                case CENTIMETERS:
                    return value;
                case METERS:
                    return value * 100;
                default:
                    throw DistanceUnit.prepareUnsupportedException(source, this);
            }
        }
    };

    private static UnsupportedOperationException prepareUnsupportedException(DistanceUnit from, DistanceUnit to) {
        final String message = String.format("Conversion from %s to %s is not supported", from, to);
        throw new UnsupportedOperationException(message);
    }
}
