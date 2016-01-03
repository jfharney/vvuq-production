package gov.ornl.vvuq.matrix;

import java.util.List;

public class SparseMatrix {
	private int numRows;
	private int numCols;
	private List<CooEntry> entries;

	public SparseMatrix(int numRows, int numCols, List<CooEntry> entries) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.entries = entries;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public List<CooEntry> getEntries() {
		return entries;
	}
}
