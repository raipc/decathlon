package io.github.raipc.decathlon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.raipc.decathlon.calculation.ConstantCoefficientsProvider;
import io.github.raipc.decathlon.calculation.DecathlonAthletesRanksCalculator;
import io.github.raipc.decathlon.calculation.ScoreCalculator;
import io.github.raipc.decathlon.input.CsvConfig;
import io.github.raipc.decathlon.input.CsvSource;
import io.github.raipc.decathlon.input.IndexBasedColumnMapper;
import io.github.raipc.decathlon.output.XmlDecathlonRankingSerializer;
import io.github.raipc.decathlon.schema.DecathlonRanking;
import io.github.raipc.decathlon.schema.DecathlonResults;
import io.github.raipc.decathlon.schema.ColumnSchema;

import static io.github.raipc.decathlon.schema.Competition.Field.DISCUS_THROW;
import static io.github.raipc.decathlon.schema.Competition.Field.HIGH_JUMP;
import static io.github.raipc.decathlon.schema.Competition.Field.JAVELIN_THROW;
import static io.github.raipc.decathlon.schema.Competition.Field.LONG_JUMP;
import static io.github.raipc.decathlon.schema.Competition.Field.POLE_VAULT;
import static io.github.raipc.decathlon.schema.Competition.Field.SHOT_PUT;
import static io.github.raipc.decathlon.schema.Competition.Track.HURDLES_110M;
import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_100M;
import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_1500M;
import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_400M;

public class Application {
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			if (args.length < 1) {
				System.out.println("Input filename is required");
			}
			System.out.println("Output XML filename is required");
			System.out.println("Usage: java -jar decathlon.jar <input.csv> <output.xml>");
			System.exit(1);
		}


		final Path file = Paths.get(args[0]);
		System.out.println(String.format("Reading performance records from file %s", file));
		final List<DecathlonResults> decathlonResults = readDecathlonResultsFromFile(file, createDefaultSchema());
		final ScoreCalculator scoreCalculator = new ScoreCalculator(new ConstantCoefficientsProvider());
		System.out.println("Ranking athletes by total score");
		final DecathlonRanking decathlonRanking = new DecathlonAthletesRanksCalculator(scoreCalculator)
			.assignRanks(decathlonResults);
		final Path outputFile = Paths.get(args[1]);
		System.out.println(String.format("Printing rank table to file %s", outputFile));
		try (Writer writer = Files.newBufferedWriter(outputFile)) {
			new XmlDecathlonRankingSerializer().serialize(decathlonRanking, writer);
		}
	}

	private static List<DecathlonResults> readDecathlonResultsFromFile(Path file, List<ColumnSchema<?>> schema) throws IOException {
		try (final BufferedReader reader = Files.newBufferedReader(file);
		     final Stream<String[]> stream = new CsvSource(new CsvConfig(0, false, ";"))
			     .readLines(reader)) {
			return stream.map(new IndexBasedColumnMapper(schema))
					.collect(Collectors.toList());
		}
	}

	/**
	 * Creates a schema to parse provided file with following example content:
	 * John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72
	 * @return schema for example file
	 */
	private static List<ColumnSchema<?>> createDefaultSchema() {
		return Arrays.asList(
			ColumnSchema.forAthleteName("unknown"),
			ColumnSchema.forPerformance("unknown", RUNNING_100M, "s.S"),
			ColumnSchema.forPerformance("unknown", LONG_JUMP, "m.m"),
			ColumnSchema.forPerformance("unknown", SHOT_PUT, "m.m"),
			ColumnSchema.forPerformance("unknown", HIGH_JUMP, "m.m"),
			ColumnSchema.forPerformance("unknown", RUNNING_400M, "s.S"),
			ColumnSchema.forPerformance("unknown", HURDLES_110M, "s.S"),
			ColumnSchema.forPerformance("unknown", DISCUS_THROW, "m.m"),
			ColumnSchema.forPerformance("unknown", POLE_VAULT, "m.m"),
			ColumnSchema.forPerformance("unknown", JAVELIN_THROW, "m.m"),
			ColumnSchema.forPerformance("unknown", RUNNING_1500M, "m.s.S")
		);
	}

}
