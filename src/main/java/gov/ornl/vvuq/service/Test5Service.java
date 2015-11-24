package gov.ornl.vvuq.service;

import java.util.Collection;

import gov.ornl.vvuq.model.Test20Response;
import gov.ornl.vvuq.model.Test5Response;

public interface Test5Service {

    Collection<Test5Response> findAll();

    Test5Response findOne(String id);

    //Greeting create(Greeting greeting);

    //Greeting update(Greeting greeting);

    //void delete(Long id);

}