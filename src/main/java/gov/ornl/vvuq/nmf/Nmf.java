package gov.ornl.vvuq.nmf;

import gov.ornl.vvuq.matrix.MatrixWrapper;

//import gov.ornl.cda.vvuq.data.matrix.MatrixWrapper;



public interface Nmf {
	NmfResult run(MatrixWrapper matrix, NmfConfig config, String type);
}
