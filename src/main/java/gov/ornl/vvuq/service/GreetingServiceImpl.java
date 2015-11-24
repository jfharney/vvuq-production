package gov.ornl.vvuq.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import gov.ornl.vvuq.model.Greeting;

@Service
public class GreetingServiceImpl implements GreetingService {

    private static Long nextId;
    private static Map<String, Greeting> greetingMap;

    
    
    private static Greeting save(Greeting greeting) {
        if (greetingMap == null) {
            greetingMap = new HashMap<String, Greeting>();
            nextId = new Long(1);
        }
        // If Update...
        if (greeting.getId() != null) {
            Greeting oldGreeting = greetingMap.get(greeting.getId());
            if (oldGreeting == null) {
                return null;
            }
            greetingMap.remove(greeting.getId());
            greetingMap.put(greeting.getId(), greeting);
            return greeting;
        }
        
        // If Create...
        greeting.setId(new String(Long.toString(nextId)));
        nextId += 1;
        greetingMap.put(greeting.getId(), greeting);
        return greeting;
    }
	
    private static boolean remove(Long id) {
        Greeting deletedGreeting = greetingMap.remove(id);
        if (deletedGreeting == null) {
            return false;
        }
        return true;
    }

    static {
        Greeting g1 = new Greeting();
        g1.setText("Hello World!");
        save(g1);

        Greeting g2 = new Greeting();
        g2.setText("Hola Mundo!");
        save(g2);
    }

    @Override
    public Collection<Greeting> findAll() {

        Collection<Greeting> greetings = greetingMap.values();

        return greetings;
    }

    @Override
    public Greeting findOne(String id) {

        Greeting greeting = greetingMap.get(id);

        return greeting;
    }

    @Override
    public Greeting create(Greeting greeting) {

        Greeting savedGreeting = save(greeting);

        return savedGreeting;
    }

    @Override
    public Greeting update(Greeting greeting) {

        Greeting updatedGreeting = save(greeting);

        return updatedGreeting;
    }

    @Override
    public void delete(Long id) {

        remove(id);

    }

}