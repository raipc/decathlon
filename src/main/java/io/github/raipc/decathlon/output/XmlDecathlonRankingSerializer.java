package io.github.raipc.decathlon.output;

import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import io.github.raipc.decathlon.schema.DecathlonRanking;

public class XmlDecathlonRankingSerializer implements DecathlonRankingSerializer {
	@Override
	public void serialize(DecathlonRanking ranking, Writer writer) {
		try {
			final JAXBContext context = JAXBContext.newInstance(DecathlonRanking.class);
			final Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			final JAXBElement<DecathlonRanking> jaxbElement = new JAXBElement<>(new QName("ranking"), DecathlonRanking.class, ranking);
			marshaller.marshal(jaxbElement, writer);
		} catch (JAXBException e) {
			throw new IllegalStateException("Cannot serialize ranking to XML", e);
		}
	}
}
