package gov.ornl.vvuq.nmf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.StringJoiner;

import org.ejml.data.DenseMatrix64F;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gov.ornl.email.SMTPAuthenticator;
import gov.ornl.vvuq.matrix.DenseMatrix;
import gov.ornl.vvuq.matrix.MatrixException;
import gov.ornl.vvuq.matrix.MatrixWrapper;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Driver {

	//used in NmfPython
	public static String py_filename = "/nmf-wrapper.py";
	
	public static void main(String [] args) {
		/*
		@implementation("nimfa_lsnmf")
		@implementation("nimfa_bd")
		@implementation("nimfa_bmf")
		@implementation("nimfa_icm")
		@implementation("nimfa_lfnmf")
		@implementation("nimfa_nsnmf")
		@implementation("nimfa_pmf")
		@implementation("nimfa_psmf")
		@implementation("nimfa_snmf")
		@implementation("nimfa_snmnmf")
		@implementation("nimfa_pmfcc")
		*/
		String [] names = {"nimfa_lsnmf"};//,"nimfa_icm","nimfa_lsnmf"};
		int [] ranks = {1,2,3,4,5};
		
		/* 1,1
		 * Total time(ns): 2329747899
			Total time(s): 2.329747899
		 * 
		 */
		/*
		 * 2,1
		 * Total time(ns): 4520351021
			Total time(s): 4.520351021
		 */
		/*
		 * 3,1
			Total time(ns): 5311046316
			Total time(s): 5.311046316
		 * 
		 * 3,2
		 * 
		 * 
			Total time(ns): 12354283427
			Total time(s): 12.354283427
		 */
		Long start = System.nanoTime();
		testNmf(names,ranks);

		Long end = System.nanoTime();
		
		//convert to seconds 
		long elapsedTime = end - start;
		double seconds = (double)elapsedTime / 1000000000.0;
		System.out.println("Total time(ns): " + (elapsedTime));
		System.out.println("Total time(s): " + seconds);
		//testPython();
		//try {
		//testMail1();
		//} catch(Exception e) {
		//	e.printStackTrace();
		//}
		
	}
	
	
	
	
	public static void testNmf(String [] names,int [] ranks) {
		System.out.println("in test nmf");
		
		//String [] names = {"nimfa_lsnmf","nimfa_nmf"};
		
		for(int i=0;i<names.length;i++) {
			
			String name = names[i];

			System.out.println("--------------");
			System.out.println("Name: " + name);
			System.out.println("--------------\n");
			
			Nmf nmf = getNmfByName(name);

			
			
			double[] rawMatrix = {
					 0, 0, 0, 0, 0,
					 0, 1, 1, 1, 0,
					 0, 1, 1, 1, 0,
					 0, 1, 1, 1, 0,
					 0, 0, 0, 0, 0
				};

			double[] rawMatrix2 = {
					 0, 0, 0, 0, 0, 0, 0,
					 0, 1, 1, 1, 1, 1, 0,
					 0, 1, 1, 1, 1, 1, 0,
					 0, 1, 1, 1, 1, 1, 0,
					 0, 1, 1, 1, 1, 1, 0,
					 0, 1, 1, 1, 1, 1, 0,
					 0, 0, 0, 0, 0, 0, 0
				};
			
			
			for(int j=0;j<ranks.length;j++) {

				int rank = ranks[j];
				System.out.println("******");
				System.out.println("Rank: " + rank);
				System.out.println("******\n");
				
				
				
				NmfConfig nmfConfig = new NmfConfig();
				nmfConfig.setRank(rank);
				
				DenseMatrix denseMatrix = new DenseMatrix(5, 5, rawMatrix);
				
				MatrixWrapper matrix = new MatrixWrapper("test-nmf-matrix", denseMatrix);

				NmfResult result = nmf.run(matrix, nmfConfig,name);
				
				try {
					
					String wTable = getTableFromMatrix(result.getW());
					String hTable = getTableFromMatrix(result.getH());
					String product = getTableFromMatrix(result.getProduct());

					System.out.println("Getting W");
					//System.out.println(result.getW());
					System.out.println("wTable: " + wTable);
					
					/*
					try {
						
						DenseMatrix64F dMatrix = result.getW().getDenseMatrix().asEjmlMatrix();

						double[] data = dMatrix.getData();
						int numCols = dMatrix.getNumCols();
						for(int k=0;k<data.length;k++) {
							System.out.println(data[k]);
						}
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					*/
					
					System.out.println("Getting W");
					//System.out.println(result.getW());
					System.out.println("wTable: " + wTable);
					//System.out.println("hTable: " + hTable);
					//System.out.println("product: " + product);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				
				System.out.println("******");
				System.out.println("End Rank: " + rank);
				System.out.println("******\n");
			}
			

			System.out.println("--------------");
			System.out.println("End Name: " + name);
			System.out.println("--------------\n");
			
		}
		
	}
	
	

	
	public static void testPython() {
		String name = "/Users/8xo/software/nimfa/nimfa/matfac.py";
		
        
		try {

			ProcessBuilder pb = new ProcessBuilder("python", name);
			Process p = pb.start();
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            System.out.println("Running Python starts: " + line);
            line = bfr.readLine();
            System.out.println("First Line: " + line);
            while ((line = bfr.readLine()) != null){
                System.out.println("Python Output: " + line);


            }
		} catch(Exception e) {
			e.printStackTrace();
		}

		
	}
	
	private static String getTableFromMatrix(MatrixWrapper matrix)
			throws MatrixException {
				DenseMatrix64F denseMatrix = matrix.getDenseMatrix().asEjmlMatrix();

				double[] data = denseMatrix.getData();
				int numCols = denseMatrix.getNumCols();

				StringJoiner rowJoiner
					= new StringJoiner("</tr><tr>", "<table><tr>", "</tr></table>");

				for (int i=0; i<data.length; i += numCols) {
					StringJoiner colJoiner = new StringJoiner("</td><td>", "<td>", "</td>");

					for (int j=0; j<numCols; ++j) {
						colJoiner.add(Double.toString(data[i + j]));
					}

					rowJoiner.add(colJoiner.toString());
				}

				return rowJoiner.toString();
	}
	
	public static Nmf getNmfByName(String name) {
		name = name.toLowerCase();

		NmfPython.Implementation impl = NmfPython.Implementation.get(name);

		return new NmfPython(impl);
		
		//return null;
	}
	//in service
	/*
	 public Nmf getNmfByName(String name) {
		name = name.toLowerCase();

		NmfPython.Implementation impl = NmfPython.Implementation.get(name);

		if (impl != null) {
			return new NmfPython(impl);
		}

		if (name.equals("spark")) {
			return new NmfSpark(sparkContext);
		}

		log.warn("No such NMF implementation exists by the name '{}'", name);
		throw new UnsupportedOperationException("No NMF implementation found");
	}
	 */
	
	//in main controller
	/*
	@RequestMapping(value = {"/test/nmf"}, method = RequestMethod.GET)
	public String testNmf(@RequestParam(value="name") String name)
	throws MatrixException {

		Nmf nmf = nmfService.getNmfByName(name);

		NmfConfig nmfConfig = new NmfConfig() {
			public int getRank() { return 1; }
		};

		double[] rawMatrix = {
			 0, 0, 0, 0, 0,
			 0, 1, 1, 1, 0,
			 0, 1, 1, 1, 0,
			 0, 1, 1, 1, 0,
			 0, 0, 0, 0, 0
		};

		DenseMatrix denseMatrix = new DenseMatrix(5, 5, rawMatrix);
		MatrixWrapper matrix = new MatrixWrapper("test-nmf-matrix", denseMatrix);

		NmfResult result = nmf.run(matrix, nmfConfig);

		return (getTableFromMatrix(result.getW()) +
		        getTableFromMatrix(result.getH()) +
		        getTableFromMatrix(result.getProduct()));
	}
	*/
}
