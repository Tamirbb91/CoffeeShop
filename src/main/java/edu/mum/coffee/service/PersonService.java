package edu.mum.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> findByEmail(String email) {
		List<Person> people = personRepository.findByEmail(email);
		return people;
	}

	@Secured(value = "hasAuthority('ROLE_ADMIN')")
	public Person findById(Long id) {
		Person person = personRepository.findOne(id);
		return person;
	}

	@Secured(value = "hasAuthority('ROLE_ADMIN')")
	public void removePerson(Person person) {
		personRepository.delete(person);
	}

	@Secured(value = "hasAuthority('ROLE_ADMIN')")
	public List<Person> getUsers(){
		return personRepository.findAll();
	}

}
