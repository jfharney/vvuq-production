package gov.ornl.vvuq.service;

import java.util.Collection;

import gov.ornl.vvuq.model.Test20Response;

public interface Test20Service {

    Collection<Test20Response> findAll();

    Test20Response findOne(String id);

    //Greeting create(Greeting greeting);

    //Greeting update(Greeting greeting);

    //void delete(Long id);

}