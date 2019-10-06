package io.github.raipc.decathlon.input;

/**
 * ColumnSplitter that doesn't support CSV escaping
 */
public class SimpleColumnSplitter implements ColumnSplitter {
	private final String character;

	SimpleColumnSplitter(String character) {
		this.character = character;
	}

	@Override
	public String[] split(String row) {
		return row.split(character);
	}
}
