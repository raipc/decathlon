package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.TrackTimeUnit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class TrackTimeUnitTest {
    @ParameterizedTest
    @CsvSource({
            "NANOSECONDS,NANOSECONDS,12.0,12.0",
            "SECONDS,SECONDS,12.0,12.0",
            "NANOSECONDS,SECONDS,12000.0,0.000012",
            "SECONDS,NANOSECONDS,12.0,12000000000.0",
    })
    public void testConvert(TrackTimeUnit from, TrackTimeUnit to, double value, double expected) {
        assertThat(to.convert(value, from), is(expected));
    }
}