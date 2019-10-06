package io.github.raipc.decathlon.input;

import java.io.Reader;
import java.util.stream.Stream;

public interface LinesProvider {
    Stream<String> readLines(Reader reader);
}
