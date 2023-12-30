package com.nitesh.springBootTestCase.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nitesh.springBootTestCase.entity.Person;
import com.nitesh.springBootTestCase.repository.PersonRepo;
import com.nitesh.springBootTestCase.service.PersonService;

//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
class PersonServiceTest {

	@Mock
	private PersonRepo personRepo;

	
	private PersonService personService;
	
//	@Before
//    public void setups() {
//        MockitoAnnotations.initMocks(this);
//    }
	
	@BeforeEach
	void setUp() {
		personService = new PersonService(personRepo);
	}

	@Test
	void getAllPerson() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person(1, "nitesh", "surat"));
		persons.add(new Person(2, "raj", "bardoli"));
		persons.add(new Person(3, "kishan", "vapi"));
		when(personRepo.findAll()).thenReturn(persons);
		
		List<Person> result = personService.getAllPerson();
		assertEquals(3, result.size());
	}
	

	@Test
	void getPersonById() {
		
		Person person = new Person(1,"sd","sds");
//		personRepo.save(person);
//		assertEquals(0, 0);
//		personRepo.save(new Person(1,"sd","sds"));
		when(personRepo.findById(1)).thenReturn(Optional.of(person));
		Optional<Person> findByPersonId = personService.findByPersonId(1);
		personService.findByPersonId(1);
		
		assertEquals("sd", findByPersonId.get().getPersonName());
	}
	
}