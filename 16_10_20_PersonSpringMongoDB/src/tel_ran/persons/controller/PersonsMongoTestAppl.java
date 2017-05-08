package tel_ran.persons.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.persons.model.dao.PersonsMongoDB;
import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Child;
import tel_ran.persons.model.entities.Employee;
import tel_ran.persons.model.entities.Person;
import tel_ran.persons.repo.PersonRepository;

public class PersonsMongoTestAppl {

	public static void main(String[] args) {
		Person[] persons = { new Child(123, "Moshe", 2011, new Address("Rehovot", "Plaut", 10), "tel-ran"),
				new Employee(124, "Vasya", 1980, new Address("Rehovot", "Plaut", 10), "Tel-Ran", 15000),
				new Child(125, "Sara", 2013, new Address("Rehovot", "Plaut", 10), "none"),
				new Child(126, "Olya", 2010, new Address("Beersheva", "Yelim", 3), "Klita"),
				new Child(127, "Sasha", 2012, new Address("Beersheva", "Yelim", 3), "Klita"),
				new Employee(128, "David", 1970, new Address("Beersheva", "Yelim", 3), "Motorola", 20000),
				new Child(129, "Tolya", 2010, new Address("Rehovot", "Plaut", 10), "Salut"),
				new Employee(130, "Serge", 1975, new Address("Beersheva", "Yelim", 3), "Motorola", 18000) };
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");	
		//---В классе PersonsMongoDB удалили сеттер
		//---PersonRepository repository = ctx.getBean(PersonRepository.class);
		PersonsMongoDB personsMongo = ctx.getBean(PersonsMongoDB.class);
//		for (Person person : persons)
//			personsMongo.addPerson(person);
		//---personsMongo.setPersons(repository);
		Iterable<Person> allPersons = personsMongo.getAllPersons();
		for (Person person : allPersons)
			System.out.println(person);
		ctx.close();
	}

}
