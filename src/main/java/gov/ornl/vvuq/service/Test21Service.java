package gov.ornl.vvuq.service;

import java.util.Collection;

import gov.ornl.vvuq.model.Test20Response;
import gov.ornl.vvuq.model.Test21Response;

public interface Test21Service {

    Collection<Test21Response> findAll();

    Test21Response findOne(String id);

    //Greeting create(Greeting greeting);

    //Greeting update(Greeting greeting);

    //void delete(Long id);

}