import java.util.*;
import java.util.Map.Entry;
public class SecureConfiguration {
Map<String,String> userMap;

public SecureConfiguration(Map<String, String> userMap) {
	super();
	this.userMap = userMap;
}

public void setUserMap(Map<String, String> userMap) {
	this.userMap = userMap;
}
public boolean containsUser(String user, String password){
	return (userMap.containsKey(user) && (userMap.get(user).equals(password)));
}

public void displayUsers(){
	for (Entry<String, String> user : userMap.entrySet()){
		System.out.println("User: "+user.getKey() +"; password: "+user.getValue());
	}
}
}
