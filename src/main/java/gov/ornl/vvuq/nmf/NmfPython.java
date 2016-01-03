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

	public NmfResult run(MatrixWrapper matrix, NmfConfig config, String type) {
		
		
		try {

			String implName = implementation.getName();

			//System.out.println("\n\nimplementation " + implementation.getName() + "\n");
			
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
				                     "--func", "non-standard",
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
			/*
			*/
			
			
			
			return new NmfResult(w, h);

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
