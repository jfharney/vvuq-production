package gov.ornl.vvuq.matrix;


import org.ejml.data.DenseMatrix64F;

public class DenseMatrix {
	private double[] data;
	private int numRows;
	private int numCols;

	public DenseMatrix() {
		this(0, 0, new double[0]);
	}

	public DenseMatrix(int numRows, int numCols, double[] data) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.data = data;
	}

	public DenseMatrix(DenseMatrix64F denseMatrix) {
		this.data = denseMatrix.getData();
		this.numRows = denseMatrix.getNumRows();
		this.numCols = denseMatrix.getNumCols();
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public double[] getData() {
		return data;
	}

	public DenseMatrix64F asEjmlMatrix() {
		boolean rowMajor = true;

		return new DenseMatrix64F(numRows, numCols, rowMajor, data);
	}
}
