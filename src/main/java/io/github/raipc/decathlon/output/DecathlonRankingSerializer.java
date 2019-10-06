package io.github.raipc.decathlon.output;

import io.github.raipc.decathlon.schema.DecathlonRanking;

import java.io.Writer;

public interface DecathlonRankingSerializer {
    void serialize(DecathlonRanking ranking, Writer writer);
}
