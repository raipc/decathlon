package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.DistanceUnit;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public enum DistancePattern {
    METERS_DOT_SEPARATED("m.m") {
        @Override
        public DistanceParser createParser() {
            return new DistanceParser(
                    DistancePattern.createNumberFormat('.'), DistanceUnit.METERS);
        }
    },
    CENTIMETERS_DOT_SEPARATED("c.c") {
        @Override
        public DistanceParser createParser() {
            return new DistanceParser(
                    DistancePattern.createNumberFormat('.'), DistanceUnit.CENTIMETERS);
        }
    },
    METERS_COMMA_SEPARATED("m,m") {
        @Override
        public DistanceParser createParser() {
            return new DistanceParser(
                    DistancePattern.createNumberFormat(','), DistanceUnit.METERS);
        }
    },
    CENTIMETERS_COMMA_SEPARATED("c,c") {
        @Override
        public DistanceParser createParser() {
            return new DistanceParser(
                    DistancePattern.createNumberFormat(','), DistanceUnit.CENTIMETERS);
        }
    };

    private final String pattern;

    DistancePattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    private static NumberFormat createNumberFormat(char decimalSeparator) {
        final DecimalFormat decimalFormat = new DecimalFormat();
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator(decimalSeparator);
        decimalFormat.setDecimalFormatSymbols(symbols);
        return decimalFormat;
    }

    public abstract DistanceParser createParser();

    public static DistancePattern parse(String pattern) {
        for (DistancePattern value : DistancePattern.values()) {
            if (value.pattern.equals(pattern)) {
                return value;
            }
        }
        final String supportedPatterns = Arrays.stream(DistancePattern.values())
                .map(DistancePattern::getPattern)
                .collect(Collectors.joining(", "));
        throw new IllegalArgumentException(
                String.format("Distance pattern '%s' is not supported. Supported patterns: %s", pattern, supportedPatterns));
    }
}
