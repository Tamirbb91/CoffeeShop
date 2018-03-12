package edu.mum.coffee.restcontroller;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("rest/person/")
public class PersonRestController {

    @Autowired
    PersonService personService;

    @PostMapping(path = "savePerson")
    public @ResponseBody
    Person save(@RequestBody @Valid Person person){
        try {
            return personService.savePerson(person);
        } catch (Exception e){

        }
        return null;
    }

    @PostMapping(path = "removePerson")
    public void delete(@RequestBody @Valid Person person){
        personService.removePerson(person);
    }


    @GetMapping(path = "findById/{id}")
    public @ResponseBody Person findById(@PathVariable long id){
        return personService.findById(id);
    }

    @GetMapping(path = "findByEmail/{email:.*}")
    public @ResponseBody List<Person> findByEmail(@PathVariable String email){
        List<Person> people = personService.findByEmail(email);
        return people;
    }
}
