package tel_ran.persons.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tel_ran.persons.model.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	Iterable<Person> findByName(String name);
	Iterable<Person> findByAddressCity(String city);
	Iterable<Person> findByBirthYearBetweenOrderByBirthYear(int from, int to);
	//имя может быть любое если есть аннотация
	@Query("{'salary':{'$lt':?0}}")
	Iterable<Person> findBySalaryLessThan(int salary);
}
