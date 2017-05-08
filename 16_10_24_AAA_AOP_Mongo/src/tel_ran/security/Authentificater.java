package tel_ran.security;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import tel_ran.security.repo.UserRepository;

public class Authentificater {
@Autowired
UserRepository users;

Map<Object, String> authObjects = new HashMap<>();//key - reference to object, value - role

public Authentificater(){};

public UserAccount getAccount(String id){
	return users.findOne(id);
}

public boolean addUserAccount(UserAccount userAccount){
	if (userAccount==null || users.exists(userAccount.getUserName()))
		return false;
	users.save(userAccount);
	return true;
}

//returns true if password matches name
public boolean authenticate(String name, String password, Object object){
	boolean res=false;
	UserAccount acc = getAccount(name);
	if ((acc!=null) && (acc.getPassword()!=null) && (acc.getPassword().equals(password))){
		String role = acc.getRole();
		if (role!=null){
			authObjects.put(object, role);
			res=true;
		}
	}		
return res;	
}

//returns role for a given object or null
public String getRole(Object obj){	
return authObjects.get(obj);	
}
}
