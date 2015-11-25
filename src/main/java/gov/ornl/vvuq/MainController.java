package gov.ornl.vvuq;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.ornl.vvuq.model.Greeting;
import gov.ornl.vvuq.model.Test20Request;
import gov.ornl.vvuq.model.Test20Response;
import gov.ornl.vvuq.model.Test23Request;
import gov.ornl.vvuq.model.Test23Response;
import gov.ornl.vvuq.model.Test3Request;
import gov.ornl.vvuq.model.Test3Response;
import gov.ornl.vvuq.service.GreetingService;
import gov.ornl.vvuq.service.Test20Service;
import gov.ornl.vvuq.service.Test23Service;
import gov.ornl.vvuq.service.Test3Service;

@Controller
public class MainController {

	@Autowired
	private GreetingService greetingService;

	
	@Autowired
	private Test3Service test3Service;

	@Autowired
	private Test23Service test23Service;
	
	@RequestMapping("/")
    //public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    public String index() {
	//model.addAttribute("name", name);
        return "index";
    }
	

    

    @RequestMapping(value="/test3",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Test23Response>> test3(@RequestBody Test23Request test23Request) {

       	
       	//Map<String,Test23Response> test23ResponseMap = test23Service.getTest23ResponseMap();
       	
       	Collection<Test23Response> responses = test23Service.findAll();
       	
       	
       	return new ResponseEntity<Collection<Test23Response>>(responses,
                   HttpStatus.OK);
       	
       	
       }
       

    @RequestMapping(value="/test4",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Test23Response>> test4(@RequestBody Test23Request test23Request) {

       	
       	//Map<String,Test23Response> test23ResponseMap = test23Service.getTest23ResponseMap();
       	
       	Collection<Test23Response> responses = test23Service.findAll();
       	
       	
       	return new ResponseEntity<Collection<Test23Response>>(responses,
                   HttpStatus.OK);
       	
       	
       }
       
    @RequestMapping(value="/test5",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Test23Response>> test5(@RequestBody Test23Request test23Request) {

       	
       	//Map<String,Test23Response> test23ResponseMap = test23Service.getTest23ResponseMap();
       	
       	Collection<Test23Response> responses = test23Service.findAll();
       	
       	
       	return new ResponseEntity<Collection<Test23Response>>(responses,
                   HttpStatus.OK);
       	
       	
       }
       
    
    @RequestMapping(value="/test20",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test20Response> test20(@RequestBody Test20Request test20Request) {

    	//inputFile
    	String inputFile = test20Request.getInputFile();
    	
    	
    	Test20Response response = new Test20Response();
    	
    	//read from file here?
    	
    	
    	response.setInputFile(inputFile);
    	
    	response.setNumRows("1000");
    	
    	response.setQueryTime("100ms");
    	
    	return new ResponseEntity<Test20Response>(response,
                HttpStatus.OK);
    	
    	
    }
    

    @RequestMapping(value="/test21",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test20Response> test21(@RequestBody Test20Request test20Request) {

    	//inputFile
    	String inputFile = test20Request.getInputFile();
    	
    	
    	Test20Response response = new Test20Response();
    	
    	//read from file here?
    	
    	
    	response.setInputFile(inputFile);
    	
    	response.setNumRows("100000");
    	
    	response.setQueryTime("100hr");
    	
    	return new ResponseEntity<Test20Response>(response,
                HttpStatus.OK);
    	
    	
    }
    
    
    @RequestMapping(value="/test23",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Test23Response>> test23(@RequestBody Test23Request test23Request) {

    	
    	//Map<String,Test23Response> test23ResponseMap = test23Service.getTest23ResponseMap();
    	
    	Collection<Test23Response> responses = test23Service.findAll();
    	
    	
    	return new ResponseEntity<Collection<Test23Response>>(responses,
                HttpStatus.OK);
    	
    	
    }
    
    
    /*
    public static void main(String [] args) {
    	try {
    		boolean inResults = false;
    		
    		String responseDir2 = System.getProperty("user.dir");
    		String responseFileName2 = responseDir2 + "/files/" + "keywords.txt";
    		
			for (String line : Files.readAllLines(Paths.get(responseFileName2))) {
				
				Test23Response response = new Test23Response();
				System.out.println("line: " + line);
				String [] arr = line.split(" ");
				StringTokenizer tokens = new StringTokenizer(line);
				System.out.println(tokens.countTokens());
				//System.out.println(arr.length);
				
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    */
    
    @RequestMapping(
            value = "/keywordset",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getKeywordSets() {

    	
    	System.out.println("In keywordset service");
    	
    	
    	
        Collection<Greeting> greetings = greetingService.findAll();

        return new ResponseEntity<Collection<Greeting>>(greetings,
                HttpStatus.OK);
    }
    
    
    
    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings() {

    	
    	System.out.println("In service");
    	
        Collection<Greeting> greetings = greetingService.findAll();

        return new ResponseEntity<Collection<Greeting>>(greetings,
                HttpStatus.OK);
    }
    
    
    /*
    private String [] keywordHelper(String [] selected_keywords, String [] all_keywords) {
    	
    	Vector<String> unselected_keywords_vector = new Vector<String>();
    	
    	boolean isSelected = false;
    	for(int i=0;i<all_keywords.length;i++) {

    		//System.out.println("i: " + i + " keyword: " + all_keywords[i]);
    		
    		for(int j=0;j<selected_keywords.length;j++) {
    			//System.out.println("\tSel: " + selected_keywords[j] + " all: " + all_keywords[i] + " " + (selected_keywords[j].equals(all_keywords[i])));
    			if(selected_keywords[j].equals(all_keywords[i])) {
    				isSelected = true;
    			}
    		}
    		//System.out.println("Isselected: " + isSelected);
    		if(!isSelected) {
    			unselected_keywords_vector.addElement(all_keywords[i]);
    		}
    		isSelected = false;
    	}
    	//System.out.println(unselected_keywords_vector);
    	String [] unselected_keywords = new String [unselected_keywords_vector.size()];
    	for(int i=0;i<unselected_keywords.length;i++) {
    		unselected_keywords[i] = unselected_keywords_vector.get(i);
    	}
    	
    	return unselected_keywords;
    }
    */
	

    /*
    @RequestMapping(value="/test20", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	//public ResponseEntity<Test20Response> getTest20(@PathVariable("id") String id) {
    public ResponseEntity<Test20Response> getTest20() {
		
    	
		//Collection<Test20Response> test20Responses = test20Service.findAll();

		Test20Response test20Response = new Test20Response();

		test20Response.setInputFile("inputFile");
		test20Response.setNumRows("numRows");
		test20Response.setQueryTime("queryTime");
		//test20Response.setId("id");
		//test20Response.setText("text");
		
		return new ResponseEntity<Test20Response>(test20Response, HttpStatus.OK);
		
	}
    */
	
    /*
    @RequestMapping(value="/test3", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	//public ResponseEntity<Test20Response> getTest20(@PathVariable("id") String id) {
    public ResponseEntity<Test3Response> getTest3() {
		
		
		Test20Response test20Response = test20Service.findOne(id);
		if(test20Response == null) {
			return new ResponseEntity<Test20Response>(HttpStatus.NOT_FOUND);
		}
		
    	
		Collection<Test3Response> test3Responses = test3Service.findAll();

		Test3Response test3Response = new Test3Response();
		
		test3Response.setId("id");
		test3Response.setText("text");
		
		return new ResponseEntity<Test3Response>(test3Response, HttpStatus.OK);
		
	}
    */
    
	
	/*
    @RequestMapping(value="/test3",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test3Response> test3(@RequestBody Test3Request test3Request) {

    	String [] selected_keywords = test3Request.getSelected_keywords();
    	String [] all_keywords = test3Request.getAll_keywords();
    	String name = test3Request.getName();
    	
		
		//System.out.println("Test 3 Launch ");
		//System.out.println("Number of selected eywords given " + selected_keywords.length);
		for(int i=0;i<selected_keywords.length;i++) {
			//System.out.println("selected key word: " + i + " " + selected_keywords[i]);
		}
		String [] unselected_keywords = this.keywordHelper(selected_keywords, all_keywords);
		for(int i=0;i<unselected_keywords.length;i++) {
			//System.out.println("unselected key word: " + i + " " + unselected_keywords[i]);
		}
		
		
		//calls to the scala code/escore api
		Random rand = new Random();
		DecimalFormat df2 = new DecimalFormat("00.00000");
		
		double r_selected_double =  (double)Math.round(rand.nextDouble() * 10000d) / 10000d - .2;
		df2.format(r_selected_double);
		double r_unselected_double = (double)Math.round(rand.nextDouble() * 10000d) / 10000d - .2;
		df2.format(r_unselected_double);
		double r_all_double = (double)Math.round(rand.nextDouble() * 10000d) / 10000d;
		df2.format(r_all_double);
		

		boolean test3 = true;
		if(r_all_double > (r_selected_double + r_unselected_double)) {
			test3 = false;
		}
		//System.out.println("test3 " + test3);

		String r_selected = Double.toString(r_selected_double);
		String r_unselected = Double.toString(r_unselected_double);
		String r_all = Double.toString(r_all_double);
		
		Test3Response test3Response = new Test3Response();
		
		test3Response.setR_selected_list(selected_keywords);
		test3Response.setR_unselected_list(unselected_keywords);
		test3Response.setR_selected(r_selected);
		test3Response.setR_unselected(r_unselected);
		test3Response.setR_all(r_all);
		test3Response.setTest3(test3);
		
		
		return new ResponseEntity<Test3Response>(test3Response, HttpStatus.CREATED);
	}
    */
    
}