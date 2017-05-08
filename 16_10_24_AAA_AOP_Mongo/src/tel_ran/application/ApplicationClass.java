package tel_ran.application;

import tel_ran.security.Authentificater;
import tel_ran.security.Authorized;

public class ApplicationClass {
Authentificater authent;

public ApplicationClass(Authentificater authent) {
	super();
	this.authent = authent;
}
//calls authenticate method of the authenticater
public boolean login(String name, String password){
	return authent.authenticate(name, password, this);	
}
@Authorized
public void set1(){
	System.out.println("set1");
}
public void set2(){
	System.out.println("set2");
}
public void get1(){
	System.out.println("get1");
}
}
