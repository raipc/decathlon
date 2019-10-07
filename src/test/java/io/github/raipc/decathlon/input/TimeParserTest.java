package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.TrackTimeUnit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

class TimeParserTest {
    @ParameterizedTest
    @CsvSource({"m:s.S,3:45.278,225.278", "m.s.S,03.45.278,225.278", "s.S,65.12,65.12"})
    public void testParseTimePerformance(String pattern, String value, double expectedSeconds) {
        final long nanos = new TimeParser(pattern).parseNanos(value);
        final double seconds = TrackTimeUnit.SECONDS.convert(nanos, TrackTimeUnit.NANOSECONDS);
        assertThat(seconds, is(closeTo(expectedSeconds, 0.0000001)));
    }
}