package tel_ran.security;

import java.util.*;

public class Authentificater {
Map<String, String> rulesPasswords = new HashMap<>();//key - role, value-password
Map<Object, String> authObjects = new HashMap<>();//key - reference to object, value - role
public Authentificater(){};
public Authentificater(Map<String, String> rulesPasswords) {
	super();
	this.rulesPasswords = rulesPasswords;
}

//returns true if password matches role
public boolean authenticate(String role, String password, Object object){
	boolean res=false;
	if (rulesPasswords.containsKey(role)){
		String pass = rulesPasswords.get(role);
			if ((pass!=null) && (pass.equals(password))){
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
