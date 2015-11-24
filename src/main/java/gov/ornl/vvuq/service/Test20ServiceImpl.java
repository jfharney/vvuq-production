package gov.ornl.vvuq.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import gov.ornl.vvuq.model.Test20Response;

public class Test20ServiceImpl {
	
	
}
/*
@Service
public class Test20ServiceImpl implements Test20Service {

    private static Long nextId;
    private static Map<String, Test20Response> test20ResponseMap;

    
    
    private static Test20Response save(Test20Response test20Response) {
        
        // If Create...
        test20ResponseMap.put(test20Response.getInputFile(), test20Response);
        return test20Response;
    }
	
    
    static {
        Test20Response test20Response1 = new Test20Response();
        test20Response1.setInputFile("inputFile");
        test20Response1.setNumRows("numRows");
        test20Response1.setQueryTime("queryTime");
        
        save(test20Response1);
    }

    
    @Override
    public Collection<Test20Response> findAll() {

        Collection<Test20Response> test20Responses = test20ResponseMap.values();

        return test20Responses;
    }
	
    
    @Override
    public Test20Response findOne(String id) {

        Test20Response greeting = test20ResponseMap.get(id);

        return greeting;
    }

    
    
    @Override
    public Test20Response create(Test20Response Test20Response) {

        Test20Response savedTest20Response = save(Test20Response);

        return savedTest20Response;
    }

    @Override
    public Test20Response update(Test20Response Test20Response) {

        Test20Response updatedTest20Response = save(Test20Response);

        return updatedTest20Response;
    }

    @Override
    public void delete(Long id) {

        remove(id);

    }
	
}
*/