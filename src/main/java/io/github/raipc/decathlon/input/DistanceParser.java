package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.DistanceUnit;

import java.text.NumberFormat;
import java.text.ParseException;

public class DistanceParser {
    private final NumberFormat numberFormat;
    private final DistanceUnit unit;

    public DistanceParser(NumberFormat numberFormat, DistanceUnit unit) {
        this.numberFormat = numberFormat;
        this.unit = unit;
    }

    public double parseDistance(String value) {
        try {
            return numberFormat.parse(value).doubleValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    String.format("Could not parse value '%s'", value), e);
        }
    }

    public DistanceUnit getUnit() {
        return unit;
    }
}
