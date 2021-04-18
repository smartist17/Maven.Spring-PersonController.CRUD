package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;
    // COMMAND: http://localhost:8090/people?firstName=James&lastName=Bond
    //@RequestMapping(value = "/people", method = RequestMethod.POST)
    @PostMapping("/people")
    public Person create(Person p){
        return personRepository.save(p);
    }
    // COMMAND: http://localhost:8090/people/2      //to get person with id=2
    //@RequestMapping(value = "/people{id}",method = RequestMethod.GET)
    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable Long id){
        return personRepository.findOne(id);
    }
    // COMMAND: http://localhost:8090/people
    //@RequestMapping(value = "/people",method = RequestMethod.GET)
    @GetMapping("/people")
    public List<Person> getPersonList(){
        List<Person> returnList = new ArrayList<>();
        for(Person p : personRepository.findAll()){
            returnList.add(p);
        }
        return returnList;
    }
    // COMMAND: http://localhost:8090/people/1?firstName=Chris&lastName=Allen   //changes person with id=1 to Chris Allen
    //@RequestMapping(value = "/people/{id}",method = RequestMethod.PUT)
    @PutMapping("/people/{id}")
    public Person updatePerson(@PathVariable Long id,Person p){
        Person temp = personRepository.findOne(id);
        temp.setFirstName(p.getFirstName());
        temp.setLastName(p.getLastName());
        return personRepository.save(temp);
    }
    // COMMAND: http://localhost:8090/people/2     //to delete person with id=2
    //@RequestMapping(value = "/people/{id}",method = RequestMethod.DELETE)
    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.delete(id);
    }
}