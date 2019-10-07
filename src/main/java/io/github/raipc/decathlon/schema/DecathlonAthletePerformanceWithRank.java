package io.github.raipc.decathlon.schema;

import io.github.raipc.decathlon.output.ToStringXmlAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {"rank", "athlete", "score", "performanceRecords"})
public class DecathlonAthletePerformanceWithRank {
    private final Rank rank;
    private final String athlete;
    private final List<PerformanceRecord<?>> performanceRecords;
    private final int score;

    public DecathlonAthletePerformanceWithRank() { // default constructor for XmlType
        this(null, null, Collections.emptyList(), 0);
    }

    public DecathlonAthletePerformanceWithRank(Rank rank, String athlete, List<PerformanceRecord<?>> performanceRecords, int score) {
        this.rank = rank;
        this.athlete = athlete;
        this.performanceRecords = performanceRecords;
        this.score = score;
    }

    @XmlElement
    @XmlJavaTypeAdapter(ToStringXmlAdapter.class)
    public Rank getRank() {
        return rank;
    }

    @XmlElement
    public String getAthlete() {
        return athlete;
    }

    @XmlElementWrapper(name = "performanceRecords")
    @XmlElement(name = "performanceRecord")
    public List<PerformanceRecord<?>> getPerformanceRecords() {
        return performanceRecords;
    }

    @XmlElement
    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecathlonAthletePerformanceWithRank that = (DecathlonAthletePerformanceWithRank) o;
        return score == that.score &&
                rank.equals(that.rank) &&
                athlete.equals(that.athlete) &&
                performanceRecords.equals(that.performanceRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, athlete, performanceRecords, score);
    }

    @Override
    public String toString() {
        return "DecathlonAthletesRanking{" +
                "rank=" + rank +
                ", athlete='" + athlete + '\'' +
                ", performanceRecords=" + performanceRecords +
                ", score=" + score +
                '}';
    }
}
