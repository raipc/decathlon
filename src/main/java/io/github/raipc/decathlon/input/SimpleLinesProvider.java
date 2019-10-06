package io.github.raipc.decathlon.input;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.stream.Stream;

/**
 * LinesProvider implementation that doesn't support CSV escaping rules,
 * i.e. all LF characters are considered row separators
 */
public class SimpleLinesProvider implements LinesProvider {
    @Override
    public Stream<String> readLines(Reader reader) {
        final BufferedReader bufferedReader = reader instanceof BufferedReader ?
                (BufferedReader) reader :
                new BufferedReader(reader);
        return bufferedReader.lines();
    }
}
