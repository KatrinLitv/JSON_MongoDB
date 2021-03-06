package tel_ran.persons.entities;

public class Child extends Person {
private String kindergarten;

public Child(int id, String name, int birthYear, String kindergarten) {
	super(id, name, birthYear);
	this.kindergarten = kindergarten;
}

public Child(){};

@Override
public String toString() {
	return "Child [kindergarten=" + kindergarten + ", getId()=" + getId() + ", getName()=" + getName()
			+ ", getBirthYear()=" + getBirthYear() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
			+ ", hashCode()=" + hashCode() + "]";
}

public String getKindergarten() {
	return kindergarten;
}
}
