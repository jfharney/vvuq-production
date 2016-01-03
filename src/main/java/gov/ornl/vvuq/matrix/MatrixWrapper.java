package gov.ornl.vvuq.matrix;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import org.ejml.data.DenseMatrix64F;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatrixWrapper {
	
	
	
	private static final Logger log
		= LoggerFactory.getLogger(MatrixWrapper.class);

	private String name;
	private SparseMatrix sparseMatrix;
	private DenseMatrix denseMatrix;

	
	
	public MatrixWrapper(String name) {
		this(name, new DenseMatrix());
	}

	public MatrixWrapper(String name, SparseMatrix sparseMatrix) {
		this.name = name;
		this.sparseMatrix = sparseMatrix;

		saveData();
	}

	public MatrixWrapper(String name, DenseMatrix denseMatrix) {
		this.name = name;
		this.denseMatrix = denseMatrix;

		saveData();
	}

	
	public String getName() {
		return name;
	}

	public DenseMatrix getDenseMatrix() {
		if (denseMatrix != null) {
			return denseMatrix;
		}

		return makeDenseMatrix(sparseMatrix);
	}

	public SparseMatrix getSparseMatrix() {
		if (sparseMatrix != null) {
			return sparseMatrix;
		}

		return makeSparseMatrix(denseMatrix);
	}

	public File getFile() {
		return getPath().toFile();
	}

	public void reload() {
		Path path = getPath();

		try (InputStream inputStream = Files.newInputStream(path);
		     Reader reader = new InputStreamReader(inputStream, "UTF-8")) {

			Gson gson = new Gson();
			MatrixWrapper other = gson.fromJson(reader, MatrixWrapper.class);

			name = other.name;
			sparseMatrix = other.sparseMatrix;
			denseMatrix = other.denseMatrix;

			if (sparseMatrix == null && denseMatrix == null) {
				log.warn("Both sparse matrix and dense matrix are null");
			}

		} catch (IOException ex) {
			log.error("Error reading matrix from {}", path);
		}
	}

	private Path getPath() {
		return Paths.get(String.format("MAT-%s.json", getName()));
	}

	private void saveData() {
		Gson gson = new Gson();
		Path path = getPath();

		//System.out.println("Writing to path: " + path);
		try (OutputStream outputStream = Files.newOutputStream(path);
		     Writer writer = new OutputStreamWriter(outputStream, "UTF-8")) {

			gson.toJson(this, writer);

		} catch (IOException ex) {
			log.error("Error writing matrix to {}", path);
		}
	}

	private DenseMatrix makeDenseMatrix(SparseMatrix sparseMatrix) {
		DenseMatrix64F denseMatrix = new DenseMatrix64F(sparseMatrix.getNumRows(),
		                                                sparseMatrix.getNumCols());

		for (CooEntry entry : sparseMatrix.getEntries()) {
			denseMatrix.set(entry.getRowIndex(),
			                entry.getColIndex(),
			                entry.getValue());
		}

		return new DenseMatrix(denseMatrix);
	}
	
	private SparseMatrix makeSparseMatrix(DenseMatrix denseMatrix) {
		List<CooEntry> entries = new ArrayList<>();
		DenseMatrix64F ejmlMatrix = denseMatrix.asEjmlMatrix();

		for (int i=0; i<denseMatrix.getNumRows(); ++i) {
			for (int j=0; j<denseMatrix.getNumCols(); ++j) {
				double value = ejmlMatrix.get(i, j);

				if (Math.abs(value) > 0.00001) {
					entries.add(new CooEntry(i, j, value));
				}
			}
		}

		return new SparseMatrix(denseMatrix.getNumRows(),
		                        denseMatrix.getNumCols(),
		                        entries);
	}
	
	
}
