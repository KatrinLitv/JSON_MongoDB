package tel_ran.persons.model.entities;

public class Employee extends Person {
String company;
int salary;
public Employee(int id, String name, int birthYear, Address address, String company, int salary) {
	super(id, name, birthYear, address);
	this.company = company;
	this.salary = salary;
}

@Override
public String toString() {
	return "Employee [company=" + company + ", salary=" + salary + ", id=" + id + ", name=" + name + ", birthYear="
			+ birthYear + ", address=" + address + "]";
}

public String getCompany() {
	return company;
}

public int getSalary() {
	return salary;
}

public void setCompany(String company) {
	this.company = company;
}

public void setSalary(int salary) {
	this.salary = salary;
}


}
