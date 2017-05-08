package tel_ran.persons.controller;

import tel_ran.persons.dao.PersonsMongo;
import tel_ran.persons.entities.Person;
//чтобы не указывать имя класс делается такой импорт
import static tel_ran.persons.controller.interfaces.PersonsRandomConstants.*;

public class UsagePersonsMongo {

	public static void main(String[] args) throws Exception {
		PersonsMongo personsMongo=new PersonsMongo
				("mongodb://root:Paris2005@ds053126.mlab.com:53126/","katrin_litv");
		//System.out.println(PersonsMongo.getPerson(26243));
		
		//Iterable<Person> personsAge = personsJdbc.getPersonsByAge(28,30);
		//displayPersons(personsAge);
		//displayPersons(personsMongo.getPersonsByName("name14"));
		//displayPersons(personsMongo.getPersonsByYearAndName("name14",1970));
		displayPersons(personsMongo.getPersonsByNames(new String[]{"name7" , "name14"}));
	}

	private static void displayPersons(Iterable<Person> persons) {
		for (Person person : persons){
			System.out.println(person);
		}
		
	}

}
