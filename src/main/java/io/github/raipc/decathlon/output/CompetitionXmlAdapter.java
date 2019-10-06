package io.github.raipc.decathlon.output;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import io.github.raipc.decathlon.schema.Competition;

public class CompetitionXmlAdapter extends XmlAdapter<String, Competition<?>> {
	public Competition<?> unmarshal(String s) {
		throw new UnsupportedOperationException();
	}

	public String marshal(Competition<?> competition) {
		return competition == null ? null : competition.name();
	}
}
