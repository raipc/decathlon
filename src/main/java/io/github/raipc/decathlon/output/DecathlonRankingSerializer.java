package io.github.raipc.decathlon.output;

import java.io.Writer;

import io.github.raipc.decathlon.schema.DecathlonRanking;

public interface DecathlonRankingSerializer {
	void serialize(DecathlonRanking ranking, Writer writer);
}
