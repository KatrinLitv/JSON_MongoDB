package tel_ran.security;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection="UserTable")
public class UserAccount {
@Id
String userName;
String password;
String role;

public UserAccount(){};
public UserAccount(String userName, String password, String role) {
	super();
	this.userName = userName;
	this.password = password;
	this.role = role;
}
public String getUserName() {
	return userName;
}
public String getPassword() {
	return password;
}
public String getRole() {
	return role;
}
@Override
public String toString() {
	return "UserAccount [userName=" + userName + ", password=" + password + ", role=" + role + "]";
}

}
