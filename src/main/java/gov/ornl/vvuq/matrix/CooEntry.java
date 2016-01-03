package gov.ornl.vvuq.matrix;

public class CooEntry {
	private final int rowIndex;
	private final int colIndex;
	private final double value;

	public CooEntry(int rowIndex, int colIndex, double value) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.value = value;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public double getValue() {
		return value;
	}
}
