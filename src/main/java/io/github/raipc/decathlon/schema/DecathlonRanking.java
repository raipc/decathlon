package io.github.raipc.decathlon.schema;

import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.List;

public class DecathlonRanking {
    private final List<DecathlonAthletePerformanceWithRank> entries;

    private DecathlonRanking() {
        this(Collections.emptyList());
    }

    public DecathlonRanking(List<DecathlonAthletePerformanceWithRank> results) {
        this.entries = Collections.unmodifiableList(results);
    }

    @XmlElement
    public List<DecathlonAthletePerformanceWithRank> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "DecathlonRanking{" +
                "results=" + entries +
                '}';
    }
}
