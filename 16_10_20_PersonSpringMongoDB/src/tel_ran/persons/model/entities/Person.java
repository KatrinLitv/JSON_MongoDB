package tel_ran.persons.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="personSpring_16_10_20")
public abstract class Person {
@Id
int id; //TZ
String name;
int birthYear;
Address address;

abstract public String toString();
public Person(){};
public Person(int id, String name, int birthYear, Address address) {
	super();
	this.id = id;
	this.name = name;
	this.birthYear = birthYear;
	this.address = address;
}

public int getId() {
	return id;
}

public String getName() {
	return name;
}

public int getBirthYear() {
	return birthYear;
}

public Address getAddress() {
	return address;
}

public void setName(String name) {
	this.name = name;
}

public void setAddress(Address address) {
	this.address = address;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Person other = (Person) obj;
	if (id != other.id)
		return false;
	return true;
}

}
