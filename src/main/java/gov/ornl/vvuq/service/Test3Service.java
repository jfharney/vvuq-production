package gov.ornl.vvuq.service;

import java.util.Collection;

import gov.ornl.vvuq.model.Test20Response;
import gov.ornl.vvuq.model.Test3Response;
import gov.ornl.vvuq.model.Test5Response;

public interface Test3Service {

    Collection<Test3Response> findAll();

    Test3Response findOne(String id);

    //Greeting create(Greeting greeting);

    //Greeting update(Greeting greeting);

    //void delete(Long id);

}