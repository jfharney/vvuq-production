package gov.ornl.vvuq.nmf;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.ornl.vvuq.matrix.MatrixWrapper;

public class NmfPython implements Nmf {
	public enum Implementation {
		SKLEARN("sklearn_nmf"),
		NIMFA_NMF("nimfa_nmf"),
		NIMFA_LSNMF("nimfa_lsnmf"),
		/*
		NIMFA_BD("nimfa_bd");
		*/
		NIMFA_ICM("nimfa_icm"),
		NIMFA_LFNMF("nimfa_lfnmf"),
		/*
		NIMFA_NSNMF("nimfa_nsnmf"),
		NIMFA_PMF("nimfa_pmf"),
		NIMFA_PSMF("nimfa_psmf"),
		*/
		NIMFA_SNMF("nimfa_snmf"),
		/*
		NIMFA_SNMNMF("nimfa_snmnmf"),
		*/
		NIMFA_PMFCC("nimfa_pmfcc");

		private static final Map<String, Implementation> lookup = new HashMap<>();

		static {
			for (Implementation i : Implementation.values()) {
				lookup.put(i.getName(), i);
			}
		}

		private final String name;

		private Implementation(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public static Implementation get(String name) {
			return lookup.get(name);
		}
	}

	private static final Logger log = LoggerFactory.getLogger(NmfPython.class);

	private final Implementation implementation;

	public NmfPython(Implementation implementation) {
		this.implementation = implementation;
	}
	
	
	//input: rank, two matrix filename to calculated, and the matrix type (w,h,etc)
	public NmfResult run_compare(String rank,String matrix_type,String [] alg_types) {
		
		try {
			
			String alg_typesStr = org.springframework.util.StringUtils.arrayToCommaDelimitedString(alg_types);
			
			InputStream pythonSource
			= this.getClass().getResourceAsStream(Driver.py_filename);

			ProcessBuilder processBuilder
			= new ProcessBuilder("python",
			                     "-",
			                     "--implementation", implementation.getName(),
			                     "--rank", rank,
			                     "--matrix-type", matrix_type,
			                     "--alg_typesStr", alg_typesStr
			                     );
		
			processBuilder
			.redirectInput(ProcessBuilder.Redirect.PIPE)
			.redirectOutput(ProcessBuilder.Redirect.INHERIT)
			.redirectErrorStream(true);
			
			
			System.out.println("Process starting");
			Process process = processBuilder.start();
			System.out.println("Process started");
			
			
			
			OutputStream outputStream = process.getOutputStream();


			//log.info("Copying source");
			IOUtils.copy(pythonSource, outputStream);
			outputStream.close();
			//log.info("Copied source");

			process.waitFor();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//catch (IOException ex) {
			//}
			//	throw new NmfException("Error occurred while executing Python NMF", ex);
			//
			//} catch (InterruptedException ex) {
			//	throw new NmfException("Interrupted before Python process finished", ex);
			//}*/
	
	public NmfResult run_factorize(MatrixWrapper matrix, NmfConfig config, String type, String func) {
		
		
		try {

			String implName = implementation.getName();

			//System.out.println("\n\nimplementation " + implementation.getName() + "\n");
			
			if(func.equals("compare")) {
				
				InputStream pythonSource
				= this.getClass().getResourceAsStream(Driver.py_filename);

				ProcessBuilder processBuilder
				= new ProcessBuilder("python",
				                     "-",
				                     "--implementation", implementation.getName(),
				                     "--matrix-filename", matrix.getFile().getName(),
				                     "--w-filename", "w-file",
				                     "--h-filename", "h-file",
				                     "--rank", Integer.toString(config.getRank()),
				                     "--func", func,
				                     "--type", type);
			
				processBuilder
				.redirectInput(ProcessBuilder.Redirect.PIPE)
				.redirectOutput(ProcessBuilder.Redirect.INHERIT)
				.redirectErrorStream(true);
				
				
				System.out.println("Process starting");
				Process process = processBuilder.start();
				System.out.println("Process started");
				
				
				
				OutputStream outputStream = process.getOutputStream();


				//System.out.println("Copying source");
				IOUtils.copy(pythonSource, outputStream);
				outputStream.close();
				//log.info("Copied source");

				process.waitFor();
				
				/*
			System.out.println("About to run the nmf-wrapper script");
			System.out.println("-w-filename: " + w.getFile().getName());
			
			ProcessBuilder processBuilder
				= new ProcessBuilder("python",
				                     "-",
				                     "--implementation", implementation.getName(),
				                     "--matrix-filename", matrix.getFile().getName(),
				                     "--w-filename", w.getFile().getName(),
				                     "--h-filename", h.getFile().getName(),
				                     "--rank", Integer.toString(config.getRank()),
				                     "--func", func,
				                     "--type", type);
			
			
			
			processBuilder
			.redirectInput(ProcessBuilder.Redirect.PIPE)
			.redirectOutput(ProcessBuilder.Redirect.INHERIT)
			.redirectErrorStream(true);
			
			
			System.out.println("Process starting");
			Process process = processBuilder.start();
			System.out.println("Process started");
			
			OutputStream outputStream = process.getOutputStream();


			//System.out.println("Copying source");
			IOUtils.copy(pythonSource, outputStream);
			outputStream.close();
			//log.info("Copied source");

			process.waitFor();
				*/
				
				return null;
				
			} else {
				MatrixWrapper w = new MatrixWrapper("nmf-python-w-" + implName + "-" + config.getRank());
				MatrixWrapper h = new MatrixWrapper("nmf-python-h-" + implName + "-" + config.getRank());

				
				InputStream pythonSource
					= this.getClass().getResourceAsStream(Driver.py_filename);

				System.out.println("About to run the nmf-wrapper script");
				System.out.println("-w-filename: " + w.getFile().getName());
				
				ProcessBuilder processBuilder
					= new ProcessBuilder("python",
					                     "-",
					                     "--implementation", implementation.getName(),
					                     "--matrix-filename", matrix.getFile().getName(),
					                     "--w-filename", w.getFile().getName(),
					                     "--h-filename", h.getFile().getName(),
					                     "--rank", Integer.toString(config.getRank()),
					                     "--func", func,
					                     "--type", type);
				
				
				
				processBuilder
				.redirectInput(ProcessBuilder.Redirect.PIPE)
				.redirectOutput(ProcessBuilder.Redirect.INHERIT)
				.redirectErrorStream(true);
				
				
				System.out.println("Process starting");
				Process process = processBuilder.start();
				System.out.println("Process started");
				
				OutputStream outputStream = process.getOutputStream();


				//System.out.println("Copying source");
				IOUtils.copy(pythonSource, outputStream);
				outputStream.close();
				//log.info("Copied source");

				process.waitFor();
				
				//log.info("Reloading W and H");
				w.reload();
				h.reload();
				//log.info("Reloaded W and H");
				

				return new NmfResult(w, h);
			}
			
			/*
			*/
			
			
			

		} catch(Exception e) {
			e.printStackTrace();
		}
			/*catch (IOException ex) {
		}
			throw new NmfException("Error occurred while executing Python NMF", ex);

		} catch (InterruptedException ex) {
			throw new NmfException("Interrupted before Python process finished", ex);
		}*/
		
		
		return null;
	}
	
	
	
	
	
	/*

	public NmfResult run(MatrixWrapper matrix, NmfConfig config, String type, String func) {
		
		
		try {

			String implName = implementation.getName();

			//System.out.println("\n\nimplementation " + implementation.getName() + "\n");
			
			if(func.equals("compare")) {
				
				InputStream pythonSource
				= this.getClass().getResourceAsStream(Driver.py_filename);

				ProcessBuilder processBuilder
				= new ProcessBuilder("python",
				                     "-",
				                     "--implementation", implementation.getName(),
				                     "--matrix-filename", matrix.getFile().getName(),
				                     "--w-filename", "w-file",
				                     "--h-filename", "h-file",
				                     "--rank", Integer.toString(config.getRank()),
				                     "--func", func,
				                     "--type", type);
			
				processBuilder
				.redirectInput(ProcessBuilder.Redirect.PIPE)
				.redirectOutput(ProcessBuilder.Redirect.INHERIT)
				.redirectErrorStream(true);
				
				
				System.out.println("Process starting");
				Process process = processBuilder.start();
				System.out.println("Process started");
				
				
				
				OutputStream outputStream = process.getOutputStream();


				//System.out.println("Copying source");
				IOUtils.copy(pythonSource, outputStream);
				outputStream.close();
				//log.info("Copied source");

				process.waitFor();
				
				
				return null;
				
			} else {
				MatrixWrapper w = new MatrixWrapper("nmf-python-w-" + implName + "-" + config.getRank());
				MatrixWrapper h = new MatrixWrapper("nmf-python-h-" + implName + "-" + config.getRank());

				
				InputStream pythonSource
					= this.getClass().getResourceAsStream(Driver.py_filename);

				System.out.println("About to run the nmf-wrapper script");
				System.out.println("-w-filename: " + w.getFile().getName());
				
				ProcessBuilder processBuilder
					= new ProcessBuilder("python",
					                     "-",
					                     "--implementation", implementation.getName(),
					                     "--matrix-filename", matrix.getFile().getName(),
					                     "--w-filename", w.getFile().getName(),
					                     "--h-filename", h.getFile().getName(),
					                     "--rank", Integer.toString(config.getRank()),
					                     "--func", func,
					                     "--type", type);
				
				
				
				processBuilder
				.redirectInput(ProcessBuilder.Redirect.PIPE)
				.redirectOutput(ProcessBuilder.Redirect.INHERIT)
				.redirectErrorStream(true);
				
				
				System.out.println("Process starting");
				Process process = processBuilder.start();
				System.out.println("Process started");
				
				OutputStream outputStream = process.getOutputStream();


				//System.out.println("Copying source");
				IOUtils.copy(pythonSource, outputStream);
				outputStream.close();
				//log.info("Copied source");

				process.waitFor();
				
				//log.info("Reloading W and H");
				w.reload();
				h.reload();
				//log.info("Reloaded W and H");
				

				return new NmfResult(w, h);
			}
			
			
			
			
			

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	*/
	
	
}

/*
processBuilder
	.redirectInput(ProcessBuilder.Redirect.PIPE)
	.redirectOutput(ProcessBuilder.Redirect.INHERIT)
	.redirectErrorStream(true);

log.info("Process starting");
Process process = processBuilder.start();
log.info("Process started");

OutputStream outputStream = process.getOutputStream();

log.info("Copying source");
IOUtils.copy(pythonSource, outputStream);
outputStream.close();
log.info("Copied source");

process.waitFor();

log.info("Reloading W and H");
w.reload();
h.reload();
log.info("Reloaded W and H");
*/





/*
public NmfResult run_compare(String matrix1_filename,String matrix2_filename,String matrix_type,String type) {

	
	try {

		String implName = implementation.getName();

		//System.out.println("\n\nimplementation " + implementation.getName() + "\n");
		
		//if(func.equals("compare")) {
			
		//MAT-nmf-python-h-nimfa_icm-1.json
		
		
			InputStream pythonSource
			= this.getClass().getResourceAsStream(Driver.py_filename);

			ProcessBuilder processBuilder
			= new ProcessBuilder("python",
			                     "-",
			                     "--implementation", implementation.getName(),
			                     "--matrix1-filename", matrix1_filename,
			                     "--matrix2-filename", matrix2_filename,
			                     "--matrix-type", matrix_type
			                     //"--func", func
			                     );
		
			processBuilder
			.redirectInput(ProcessBuilder.Redirect.PIPE)
			.redirectOutput(ProcessBuilder.Redirect.INHERIT)
			.redirectErrorStream(true);
			
			
			System.out.println("Process starting");
			Process process = processBuilder.start();
			System.out.println("Process started");
			
			
			
			OutputStream outputStream = process.getOutputStream();


			//System.out.println("Copying source");
			IOUtils.copy(pythonSource, outputStream);
			outputStream.close();
			//log.info("Copied source");

			process.waitFor();
			
			return null;
		
		
		

	} catch(Exception e) {
		e.printStackTrace();
	}
	return null;

}
*/
