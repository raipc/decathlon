package io.github.raipc.decathlon.output;

import io.github.raipc.decathlon.schema.Competition;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CompetitionXmlAdapter extends XmlAdapter<String, Competition<?>> {
    public Competition<?> unmarshal(String s) {
        throw new UnsupportedOperationException();
    }

    public String marshal(Competition<?> competition) {
        return competition == null ? null : competition.getDisplayName();
    }
}
