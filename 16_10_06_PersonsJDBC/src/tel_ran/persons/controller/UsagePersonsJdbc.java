package tel_ran.persons.controller;

import tel_ran.persons.dao.AnyTypeJdbc;
import tel_ran.persons.dao.PersonsJdbc;
import tel_ran.persons.entities.Person;
//чтобы не указывать имя класс делается такой импорт
import static tel_ran.persons.controller.interfaces.PersonsRandomConstants.*;

public class UsagePersonsJdbc {

	public static void main(String[] args) throws Exception {
//		PersonsJdbc personsJdbc=new PersonsJdbc();
//		//Iterable<Person> personsAge = personsJdbc.getPersonsByAge(28,30);
//		//displayPersons(personsAge);
//		displayPersons(personsJdbc.getPersonsByName("name7"));
		AnyTypeJdbc jdbc = new AnyTypeJdbc();
		System.out.println(jdbc.get(15910));
	}

	private static void displayPersons(Iterable<Person> personsAge) {
		for (Person person : personsAge){
			System.out.println(person);
		}
		
	}

}
