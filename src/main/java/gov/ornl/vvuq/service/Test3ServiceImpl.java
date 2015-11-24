package gov.ornl.vvuq.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import gov.ornl.vvuq.model.Greeting;
import gov.ornl.vvuq.model.Test20Response;
import gov.ornl.vvuq.model.Test3Response;

@Service
public class Test3ServiceImpl implements Test3Service {

	private static Map<String, Test3Response> test3ResponseMap;
	
	public Test3ServiceImpl() {
		/*
		test3ResponseMap = new HashMap<String,Test3Response>();
		System.out.println("\n\n\nHere\n\n\n");
		Test3Response test3Response1 = new Test3Response();
        test3Response1.setName("name1");
        test3Response1.setR_all("r_all1");
        test3Response1.setR_selected("r_selected1");
        test3Response1.setR_unselected("r_unselected1");
        test3ResponseMap.put(test3Response1.getName(), test3Response1);
        */
	}
	
	
	@Override
    public Collection<Test3Response> findAll() {

        Collection<Test3Response> test3Responses = test3ResponseMap.values();

        return test3Responses;
    }
	
    
    @Override
    public Test3Response findOne(String name) {

        Test3Response greeting = test3ResponseMap.get(name);

        return greeting;
    }
	
	static {
        Test3Response test3Response1 = new Test3Response();
        test3Response1.setName("name1");
        
        String [] r_selected_list1 = {"key1","key2","key3"};
        String [] r_unselected_list1 = {"key4","key5","key6"};
        
        test3Response1.setR_selected_list(r_selected_list1);
        test3Response1.setR_unselected_list(r_unselected_list1);
        
        test3Response1.setR_all("r_all1");
        test3Response1.setR_selected("r_selected1");
        test3Response1.setR_unselected("r_unselected1");
        save(test3Response1);
        

    } 
	
	private static Test3Response save(Test3Response test3Response) {
    	
		if(test3ResponseMap == null) {
			test3ResponseMap = new HashMap<String, Test3Response>();
        }
    	
        test3ResponseMap.put(test3Response.getName(), test3Response);
        return test3Response;
    }
	
	/*
	private static Test3Response save(Test3Response test3Response) {
    	
    	
        // If Create...
    	test3Response.setName("name");
    	test3Response.setR_all("r_all");
    	test3Response.setR_selected("r_selected");
    	test3Response.setR_unselected("r_unselected");
    	
        test3ResponseMap.put(test3Response.getName(), test3Response);
        return test3Response;
    }
	
	static {
        Test3Response test3Response1 = new Test3Response();
        test3Response1.setName("name1");
        test3Response1.setR_all("r_all1");
        test3Response1.setR_selected("r_selected1");
        test3Response1.setR_unselected("r_unselected1");
        save(test3Response1);

        Test3Response test3Response2 = new Test3Response();
        test3Response2.setName("name2");
        test3Response2.setR_all("r_all2");
        test3Response2.setR_selected("r_selected2");
        test3Response2.setR_unselected("r_unselected2");
        save(test3Response2);

    }
	*/
}
