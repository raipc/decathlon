package io.github.raipc.decathlon.output;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ToStringXmlAdapter extends XmlAdapter<String, Object> {
    public Object unmarshal(String s) {
        throw new UnsupportedOperationException();
    }

    public String marshal(Object o) {
        return o == null ? null : o.toString();
    }
}
