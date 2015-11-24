package gov.ornl.vvuq.service;

import java.util.Collection;
import java.util.Map;

import gov.ornl.vvuq.model.Test23Response;

public interface Test23Service {

    Collection<Test23Response> findAll();

    Test23Response findOne(String id);

	Map<String, Test23Response> getTest23ResponseMap();

    
}