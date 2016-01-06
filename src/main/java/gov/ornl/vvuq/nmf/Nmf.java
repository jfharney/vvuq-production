package gov.ornl.vvuq.nmf;

import gov.ornl.vvuq.matrix.MatrixWrapper;

//import gov.ornl.cda.vvuq.data.matrix.MatrixWrapper;



public interface Nmf {
	NmfResult run_factorize(MatrixWrapper matrix, NmfConfig config, String type, String func);

	//NmfResult run_compare(String matrix1_filename,String matrix2_filename,String matrix_type,String type);
	
	//input: rank, two matrix filename to calculated, and the matrix type (w,h,etc)
	NmfResult run_compare(String rank,String matrix_type,String [] arr);
}
