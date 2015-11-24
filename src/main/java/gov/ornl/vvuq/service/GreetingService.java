package gov.ornl.vvuq.service;

import java.util.Collection;

import gov.ornl.vvuq.model.Greeting;


public interface GreetingService {

    Collection<Greeting> findAll();

    Greeting findOne(String id);

    Greeting create(Greeting greeting);

    Greeting update(Greeting greeting);

    void delete(Long id);

}