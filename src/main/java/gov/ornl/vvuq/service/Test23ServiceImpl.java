package gov.ornl.vvuq.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import gov.ornl.vvuq.model.Test23Response;

@Service
public class Test23ServiceImpl implements Test23Service {

	public static String responseDir = "/Users/8xo/software/code-int/production/vvuq/";
	public static String responseFileName = responseDir + "response.txt";
	public static String responseDir2 = System.getProperty("user.dir");
	public static String responseFileName2 = responseDir2 + "/files/end2end/" + "end2end_output.txt";
	
	public static String responseDir3 = System.getProperty("user.dir");
	public static String responseFileName3 = responseDir3 + "/files/" + "query_results_end2end.txt";
	
	
    private static Map<String, Test23Response> test23ResponseMap;
    
    private static List<Test23Response> test23ResponseList;
    
    public Test23ServiceImpl() {
    	test23ResponseMap = new HashMap<String,Test23Response>();
        
        //List<Test23Response> examples = getFromFile();//getExamples();
        
    	//List<Test23Response> examples = getFromFile2();
    			
    	List<Test23Response> examples = getFromFile();
    	
    	test23ResponseList = examples;
    			
        for(int i=0;i<examples.size();i++) {
        	test23ResponseMap.put(examples.get(i).getName(), examples.get(i));
        }
        
    }
    
    
    
	@Override
	public Collection<Test23Response> findAll() {
		
		Collection<Test23Response> responses = this.test23ResponseList;//this.test23ResponseMap.values();

        return responses;
	}

	@Override
	public Test23Response findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
    public Map<String, Test23Response> getTest23ResponseMap() {
		if(this.test23ResponseMap == null) {
			System.out.println("Is null????");
			return null;
		} else {
			return this.test23ResponseMap;
		}
		
	}
    
    
    
    public static List<Test23Response> getFromFile2() {

    	Map<String,Test23Response> map = new HashMap<String,Test23Response>();
		List<Test23Response> responses = new ArrayList<Test23Response>();
		
    	try {
    		boolean inResults = false;
			for (String line : Files.readAllLines(Paths.get(responseFileName2))) {
				Test23Response response = new Test23Response();
				if(line.contains("XXXXX")) {
					inResults = !inResults;
					//System.out.println("line: " + line);
				}
				if(inResults) {
					//System.out.println("consume line: " + line);
					String [] arr = line.split("\\|");
					if(arr.length != 1) {

						//System.out.println("line: " + line);
						//System.out.println(arr.length);
						response.setName(arr[0]);
						response.setType(arr[1]);
						Double value = Double.parseDouble(arr[2]);
						arr[2] = Double.toString(round(value,3));
						response.setTotal_score(arr[2]);
						response.setDissemination_equipment(arr[3]);
						response.setPostproduction_equipment(arr[4]);
						response.setResearch_words_and_materials(arr[5]);
						String [] keywords = new String[arr.length-1-5];
						int j = 0;
						for(int i=6;i<arr.length;i++) {
							keywords[j] = arr[i];
							j++;
						}
						response.setKeywords(keywords);
						map.put(response.getName(), response);
						responses.add(response);
					}
				}
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    	return responses;
    }
    
    /*
    public static void main(String [] args) {
    	Test23ServiceImpl test23 = new Test23ServiceImpl();
    	
    	
    	List<Test23Response> examples = test23.getFromFile3();
    	
    }
    */
    
    public List<Test23Response> getFromFile3() {
    	
    	Map<String,Test23Response> map = new HashMap<String,Test23Response>();
		List<Test23Response> responses = new ArrayList<Test23Response>();
    	try {
			
			for (String line : Files.readAllLines(Paths.get(responseFileName3))) {
				Test23Response response = new Test23Response();
				
				String [] arr = line.split("\\|");
				if(arr.length != 1) {

					//System.out.println("line: " + line);
					//System.out.println(arr.length);
					response.setName(arr[0]);
					response.setType(arr[1]);
					Double value = Double.parseDouble(arr[2]);
					arr[2] = Double.toString(round(value,5));
					System.out.println("arr[2]: " + arr[2]);
					response.setTotal_score(arr[2]);
					response.setDissemination_equipment(arr[3]);
					response.setPostproduction_equipment(arr[4]);
					response.setResearch_words_and_materials(arr[5]);
					String [] keywords = new String[arr.length-1-5];
					int j = 0;
					for(int i=6;i<arr.length;i++) {
						keywords[j] = arr[i];
						j++;
					}
					response.setKeywords(keywords);
					map.put(response.getName(), response);
					responses.add(response);
				}
				
			}
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responses;
    }
    
    
    public List<Test23Response> getFromFile() {
    	
    	Map<String,Test23Response> map = new HashMap<String,Test23Response>();
		List<Test23Response> responses = new ArrayList<Test23Response>();
    	try {
			
			for (String line : Files.readAllLines(Paths.get(responseFileName3))) {
				Test23Response response = new Test23Response();
				
				String [] arr = line.split("\\|");
				if(arr.length != 1) {

					//System.out.println("line: " + line);
					//System.out.println(arr.length);
					response.setName(arr[0]);
					response.setType(arr[1]);
					response.setTotal_score(arr[2]);
					response.setDissemination_equipment(arr[3]);
					response.setPostproduction_equipment(arr[4]);
					response.setResearch_words_and_materials(arr[5]);
					String [] keywords = new String[arr.length-1-5];
					int j = 0;
					for(int i=6;i<arr.length;i++) {
						keywords[j] = arr[i];
						j++;
					}
					response.setKeywords(keywords);
					map.put(response.getName(), response);
					responses.add(response);
				}
				
			}
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responses;
    }
    
    public List<Test23Response> getExamples() {
    	
    	List<Test23Response> examples = new ArrayList<Test23Response>();
    	
    	Test23Response response1 = new Test23Response();
    	response1.setName("Muhammad Ibrahim");
        response1.setType("Person");
        response1.setTotal_score("0.6689582334406552");
        response1.setDissemination_equipment("3.5493046884874525E-4");
        response1.setPostproduction_equipment("0.0413875767072686");
        response1.setResearch_words_and_materials("0.6273427076033018");
        String [] keywords1 = {"Vertical electrophoresis[Research_Words_and_Materials](0.2607919859793767)",
        		"Microtitre plate[Research_Words_and_Materials](0.09387412508821041)",
        		"LB Broth[Research_Words_and_Materials](0.08935812707678348)",
        		"Centrifuge[Post-Production-Equipment](0.04038781856246073)",
        		"Pathogenicity[Research_Words_and_Materials](0.01839625656566282)"
        };
        response1.setKeywords(keywords1);
        examples.add(response1);
        
        Test23Response response2 = new Test23Response();
    	response2.setName("Nuno P Mira");
        response2.setType("Person");
        response2.setTotal_score("0.448875636061044");
        response2.setDissemination_equipment("3.5493046884874525E-4");
        response2.setPostproduction_equipment("8.948242655058361E-4");
        response2.setResearch_words_and_materials("0.4476258813266894");
        String [] keywords2 = {"Genetic engineering[Research_Words_and_Materials](0.26399276882384753)",
        		"Fermentation[Research_Words_and_Materials](0.03941157891938974)",
        		"Biosynthetic[Research_Words_and_Materials](0.031393722224471364)",
        		"High performance liquid chromatography[Research_Words_and_Materials](0.028652211426770394)",
        		"Agar plates[Research_Words_and_Materials](0.019637119014539)"
        };
        response2.setKeywords(keywords2);
        examples.add(response2);
        
        return examples;
    }
	
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}



/*
public static void main(String [] args) {
	
	System.out.println("Working Directory = " +
            System.getProperty("user.dir"));
	
	List<Test23Response> responses = getFromFile2();
	
	//System.out.println("responses: " + responses.size() + " " + responses);
	
	
}
*/

/*
public static void main(String [] args) {
	
	System.out.println("test");
	
	try {
		Map<String,Test23Response> map = new HashMap<String,Test23Response>();
		List<Test23Response> responses = new ArrayList<Test23Response>();
		for (String line : Files.readAllLines(Paths.get(responseFileName))) {
			Test23Response response = new Test23Response();
			
			String [] arr = line.split("\\|");
			if(arr.length != 1) {

				//System.out.println("line: " + line);
				//System.out.println(arr.length);
				response.setName(arr[0]);
				response.setType(arr[1]);
				response.setTotal_score(arr[2]);
				response.setDissemination_equipment(arr[3]);
				response.setPostproduction_equipment(arr[4]);
				response.setResearch_words_and_materials(arr[5]);
				String [] keywords = new String[arr.length-1-5];
				int j = 0;
				for(int i=6;i<arr.length;i++) {
					keywords[j] = arr[i];
					j++;
				}
				response.setKeywords(keywords);
				map.put(response.getName(), response);
				responses.add(response);
			}
			
		}
		System.out.println(map);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
*/

/*
name|type|total score|Dissemination-Equipment|Post-Production-Equipment|Research_Words_and_Materials|Keyword 1|Keyword 2|Keyword 3|Keyword 4|Keyword 5
 Muhammad Ibrahim|Person|0.6689582334406552|2.2794913008467648E-4|0.0413875767072686|0.6273427076033018|Vertical electrophoresis[Research_Words_and_Materials](0.2607919859793767)|Microtitre plate[Research_Words_and_Materials](0.09387412508821041)|LB Broth[Research_Words_and_Materials](0.08935812707678348)|Centrifuge[Post-Production-Equipment](0.04038781856246073)|Pathogenicity[Research_Words_and_Materials](0.01839625656566282)

 Nuno P Mira|Person|0.448875636061044|3.5493046884874525E-4|8.948242655058361E-4|0.4476258813266894|Genetic engineering[Research_Words_and_Materials](0.26399276882384753)|Fermentation[Research_Words_and_Materials](0.03941157891938974)|Biosynthetic[Research_Words_and_Materials](0.031393722224471364)|High performance liquid chromatography[Research_Words_and_Materials](0.028652211426770394)|Agar plates[Research_Words_and_Materials](0.019637119014539)

 Janusz M. Bujnicki|Person|0.44074743997989546|0.0|0.002022839281775813|0.43872460069811964|FPLC[Research_Words_and_Materials](0.26674152022993525)|Protein engineering[Research_Words_and_Materials](0.09513542067108766)|Gel filtration[Research_Words_and_Materials](0.03461948506090214)|Mutagenesis[Research_Words_and_Materials](0.009727230351199824)|Nucleic[Research_Words_and_Materials](0.006392516086789625)

 Mingming Xu|Person|0.4386340733915187|4.0224342512356666E-5|2.164648841873636E-4|0.4383773841648191|Nanobiotechnology[Research_Words_and_Materials](0.2802740624313243)|Fluorescent microscopy[Research_Words_and_Materials](0.1448857075426881)|Cell culture[Research_Words_and_Materials](0.004629672482412249)|Cell line[Research_Words_and_Materials](0.004167429527753151)|Steroid[Research_Words_and_Materials](3.859291663521654E-4)

 Arvind Rajwanshi|Person|0.4367138028489556|0.0|0.26358874936786947|0.1731250534810862|Lyophilizer[Post-Production-Equipment](0.26237345095850384)|Protein fraction[Research_Words_and_Materials](0.05443868216377771)|Gel filtration[Research_Words_and_Materials](0.03412510943699416)|Inoculum[Research_Words_and_Materials](0.017138377423324275)|Mucus[Research_Words_and_Materials](0.014942075336769053)

 Isabelle Jacques|Person|0.4339549792863697|0.002489836065647304|2.6059246092102604E-4|0.4312045507598014|Zoonosis[Research_Words_and_Materials](0.274675312026362)|DNA Hybridization[Research_Words_and_Materials](0.0577198761037235)|Phage[Research_Words_and_Materials](0.022318477473937535)|Pathogenicity[Research_Words_and_Materials](0.01934132505179003)|Vaccination[Research_Words_and_Materials](0.015490239862506758)

 Dirk G. de Rooij|Person|0.4222536446304524|0.0|2.4812454835968054E-4|0.42200552008209263|Chromatids[Research_Words_and_Materials](0.26867500510226094)|Pepsin[Research_Words_and_Materials](0.09276907499649875)|Codon[Research_Words_and_Materials](0.010105590822062643)|Exon[Research_Words_and_Materials](0.00860012117492394)|Trypsin[Research_Words_and_Materials](0.008536389626438688)
*/