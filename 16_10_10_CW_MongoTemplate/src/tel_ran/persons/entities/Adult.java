package tel_ran.persons.entities;

public class Adult extends Person {
public int salary;

public Adult(int id, String name, int birthYear, int salary) {
	super(id, name, birthYear);
	this.salary = salary;
}
public Adult(){};

@Override
public String toString() {
	return "Adult [salary=" + salary + ", getId()=" + getId() + ", getName()=" + getName() + ", getBirthYear()="
			+ getBirthYear() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
			+ hashCode() + "]";
}

public int getSalary() {
	return salary;
}

}
