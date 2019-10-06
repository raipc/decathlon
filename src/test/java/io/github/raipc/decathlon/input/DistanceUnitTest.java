package io.github.raipc.decathlon.input;

import io.github.raipc.decathlon.schema.DistanceUnit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class DistanceUnitTest {
	@ParameterizedTest
	@CsvSource({
		"METERS,METERS,12.0,12.0",
		"METERS,CENTIMETERS,12.0,1200.0",
		"CENTIMETERS,CENTIMETERS,1200.0,1200.0",
		"CENTIMETERS,METERS,1200.0,12.0",
	})
	public void testDistanceConversion(DistanceUnit from, DistanceUnit to, double value, double expected) {
		assertThat(to.convert(value, from), is(expected));
	}
}