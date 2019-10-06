package io.github.raipc.decathlon.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;

import io.github.raipc.decathlon.schema.DecathlonAthletePerformanceWithRank;
import io.github.raipc.decathlon.schema.DecathlonRanking;
import io.github.raipc.decathlon.schema.PerformanceRecord;
import io.github.raipc.decathlon.schema.Rank;
import io.github.raipc.decathlon.schema.TrackTimeUnit;
import org.apache.tika.io.IOUtils;
import org.junit.jupiter.api.Test;

import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_100M;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class XmlDecathlonRankingSerializerTest {
	@Test
	public void testSerialization() throws IOException {
		final XmlDecathlonRankingSerializer serializer = new XmlDecathlonRankingSerializer();
		final StringWriter writer = new StringWriter();
		final DecathlonRanking ranking = new DecathlonRanking(Arrays.asList(
			new DecathlonAthletePerformanceWithRank(
				new Rank(1, 1), "John Dow", Arrays.asList(
					new PerformanceRecord<>(RUNNING_100M, "10.0", 10.0, TrackTimeUnit.SECONDS)), 500),
			new DecathlonAthletePerformanceWithRank(
				new Rank(2, 2), "John Dow Jr", Arrays.asList(
					new PerformanceRecord<>(RUNNING_100M, "10.1", 10.1, TrackTimeUnit.SECONDS)), 490)
			));
		serializer.serialize(ranking, writer);
		try (final InputStream resource = getClass().getResourceAsStream("test_serialization.xml")) {
			final String content = IOUtils.toString(resource, "UTF-8");
			assertThat(writer.toString(), is(content));
		}
	}

}