package io.github.raipc.decathlon.input;

public class CsvConfig {
    final int skipNRows;
    final boolean firstRowIsHeader;
    final String columnSeparator;

    public CsvConfig(int skipNRows, boolean firstRowIsHeader, String columnSeparator) {
        this.skipNRows = skipNRows;
        this.firstRowIsHeader = firstRowIsHeader;
        this.columnSeparator = columnSeparator;
    }
}
