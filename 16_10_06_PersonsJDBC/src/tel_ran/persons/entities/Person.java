package tel_ran.persons.entities;

public class Person {
public int id;
public String name;
public int birthYear;

public int getId() {
	return id;
}

public String getName() {
	return name;
}

public int getBirthYear() {
	return birthYear;
}

@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", birthYear=" + birthYear + "]";
}

public Person(){};

public Person(int id, String name, int birthYear) {
	super();
	this.id = id;
	this.name = name;
	this.birthYear = birthYear;
}

}
