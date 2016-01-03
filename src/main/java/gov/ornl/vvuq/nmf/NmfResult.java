package gov.ornl.vvuq.nmf;


import java.util.List;
import java.util.ArrayList;

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.ornl.vvuq.matrix.DenseMatrix;
import gov.ornl.vvuq.matrix.MatrixWrapper;

public class NmfResult {
	private static final Logger log = LoggerFactory.getLogger(NmfResult.class);

	
	
	private final MatrixWrapper w;
	private final MatrixWrapper h;
	//private NmfCluster rowClusters;
	//private NmfCluster colClusters;
	private MatrixWrapper product;

	public NmfResult(MatrixWrapper w, MatrixWrapper h) {
		this.w = w;
		this.h = h;
	}

	
	public MatrixWrapper getW() {
		return w;
	}

	public MatrixWrapper getH() {
		return h;
	}
	
	public MatrixWrapper getProduct() {
		if (product != null) {
			return product;
		}

		DenseMatrix64F denseW = w.getDenseMatrix().asEjmlMatrix();
		DenseMatrix64F denseH = h.getDenseMatrix().asEjmlMatrix();

		DenseMatrix64F denseProduct
			= new DenseMatrix64F(denseW.getNumRows(), denseH.getNumCols());

		CommonOps.mult(denseW, denseH, denseProduct);

		product
			= new MatrixWrapper("nmf-result-product", new DenseMatrix(denseProduct));

		return product;
	}
	/*
	//*
	// @param matrix The matrix of features to cluster by row
	//
	private NmfCluster getClusters(DenseMatrix64F matrix) {
		int width = matrix.getNumCols();
		int height = matrix.getNumRows();
		double[] data = matrix.getData();

		double[] means = new double[width];
		int i, j;
		for (i=0; i<height; ++i) {
			for (j=0; j<width; ++j) {
				means[j] += data[i * width + j];
			}
		}

		for (i=0; i<width; ++i) {
			means[i] /= height;
		}

		for (i=0; i<width; ++i) {
			log.debug("means[{}] = {}", i, means[i]);
		}

		log.debug("Expecting {} clusters", width);

		@SuppressWarnings("unchecked")
		List<Integer>[] clusters = new ArrayList[width];

		for (i=0; i<width; ++i) {
			clusters[i] = new ArrayList<>();
		}

		for (i=0; i<height; ++i) {
			for (j=0; j<width; ++j) {
				if (data[i * width + j] > means[j]) {
					clusters[j].add(i);
					log.debug("Adding {} to cluster {}", i, j);
				}
			}
		}

		for (i=0; i<width; ++i) {
			log.debug("clusters[{}].size() = {}", i, clusters[i].size());
		}

		return new NmfCluster(matrix, clusters);
	}

	public NmfCluster getRowClusters() {
		if (rowClusters != null) {
			return rowClusters;
		}

		rowClusters = getClusters(getW().getDenseMatrix().asEjmlMatrix());
		return rowClusters;
	}

	public NmfCluster getColClusters() {
		if (colClusters != null) {
			return colClusters;
		}

		DenseMatrix64F matrix = getH().getDenseMatrix().asEjmlMatrix();

		DenseMatrix64F transposed
			= new DenseMatrix64F(matrix.getNumCols(), matrix.getNumRows());

		CommonOps.transpose(matrix, transposed);

		colClusters = getClusters(transposed);
		return colClusters;
	}
	*/
	
}
