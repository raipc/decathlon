package io.github.raipc.decathlon.input;

import java.io.Reader;
import java.util.stream.Stream;

public class CsvSource {
    private final CsvConfig csvConfig;
    private final LinesProvider linesProvider;
    private final ColumnSplitter columnSplitter;

    public CsvSource(CsvConfig csvConfig) {
        this.csvConfig = csvConfig;
        this.linesProvider = new SimpleLinesProvider();
        this.columnSplitter = new SimpleColumnSplitter(csvConfig.columnSeparator);
    }

    public Stream<String[]> readLines(Reader reader) {
        return linesProvider.readLines(reader)
                .skip(csvConfig.skipNRows + (csvConfig.firstRowIsHeader ? 1 : 0))
                .filter(CsvSource::isNotBlank)
                .map(columnSplitter::split);
    }

    private static boolean isNotBlank(String value) {
        final int length = value.length();
        for (int i = 0; i < length; i++) {
            if (value.charAt(i) > ' ') {
                return true;
            }
        }
        return false;
    }
}
