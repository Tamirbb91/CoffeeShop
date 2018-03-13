package edu.mum.coffee;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Person;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class PersonRestfulTest {
    private final String SERVER_URI = "http://localhost:8080/rest/person";
    private long personId = 1;
    private String personEmail = "changed@gmail.com";


    @Test
    public void a_savePerson(){
        RestTemplate restTemplate = new RestTemplate();

        Person person = new Person();
        person.setFirstName("Tamir");
        person.setLastName("Batmunkh");
        person.setEmail("tamiraa@gmail.com");
        person.setPhone("99180558");
        person.setEnable(true);

        Address address = new Address();
        address.setCity("Ulaanbaatar");
        address.setCountry("Mongolia");
        address.setState("Capital");
        address.setZipcode("17000");

        person.setAddress(address);

        person = restTemplate.postForObject(SERVER_URI + "/savePerson", person, Person.class);

        PrintPerson(person);
        this.personId = person.getId();
    }

    @Test
    public void b_updatePerson(){
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject(SERVER_URI + "/findById/" + personId, Person.class);

        System.out.println("Before remove");
        if(person == null){
            System.out.println("Person not found");
            return;
        } else {
            PrintPerson(person);
        }

        person.setEmail(personEmail);

        person = restTemplate.postForObject(SERVER_URI + "/savePerson", person, Person.class);

        PrintPerson(person);
    }

    @Test
    public void c_findById(){
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject(SERVER_URI + "/findById/" + personId, Person.class);

        if(person == null){
            System.out.println("Person not found");
        } else {
            PrintPerson(person);
        }

    }

    @Test
    public void d_findByEmail(){
        RestTemplate restTemplate = new RestTemplate();

        List<LinkedHashMap> peopleList = restTemplate.getForObject(SERVER_URI + "/findByEmail/" + personEmail, List.class);

        for(LinkedHashMap map : peopleList) {
            Person person = new Person();

            person.setFirstName(map.get("firstName").toString());
            person.setLastName(map.get("lastName").toString());
            person.setPhone(map.get("phone").toString());
            person.setEmail(map.get("email").toString());
            person.setEnable((Boolean) map.get("enable"));

            LinkedHashMap addressMap = (LinkedHashMap) map.get("address");

            Address address = new Address();
            address.setCity(addressMap.get("city").toString());
            address.setCountry(addressMap.get("country").toString());
            address.setState(addressMap.get("state").toString());
            address.setZipcode(addressMap.get("zipcode").toString());

            person.setAddress(address);

            PrintPerson(person);

            System.out.println("------------------------");
        }
    }

    @Test
    public void e_removePerson(){
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject(SERVER_URI + "/findById/" + personId, Person.class);

        System.out.println("Before remove");
        if(person == null){
            System.out.println("Person not found");
            return;
        } else {
            PrintPerson(person);
        }

        restTemplate.postForEntity(SERVER_URI + "/removePerson", person, Person.class);

        person = restTemplate.getForObject(SERVER_URI + "/findById/" + personId, Person.class);

        Assert.assertTrue(person == null);

    }


    public void PrintPerson(Person person){
        System.out.println("First name : " + person.getFirstName());
        System.out.println("Last name : " + person.getLastName());
        System.out.println("Email : " + person.getEmail());
        System.out.println("Phone : " + person.getPhone());
        Address address = person.getAddress();
        System.out.println("Address : ");
        System.out.println("\t City : " + address.getCity());
        System.out.println("\t Country : " + address.getCountry());
        System.out.println("\t State : " + address.getState());
        System.out.println("\t ZipCode : " + address.getZipcode());
    }
}
