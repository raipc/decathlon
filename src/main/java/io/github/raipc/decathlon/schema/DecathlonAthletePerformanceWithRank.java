package io.github.raipc.decathlon.schema;

import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import io.github.raipc.decathlon.output.ToStringXmlAdapter;

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
	public String toString() {
		return "DecathlonAthletesRanking{" +
			"rank=" + rank +
			", athlete='" + athlete + '\'' +
			", performanceRecords=" + performanceRecords +
			", score=" + score +
			'}';
	}
}
