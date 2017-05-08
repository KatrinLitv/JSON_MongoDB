package tel_ran.security.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import tel_ran.security.Accounter;
import tel_ran.security.Authentificater;

import java.util.*;

public class Authorizer {
Authentificater authent;
Accounter accounter;
//key - role name, value - set of the permitted method names
Map<String,Set<String>> rulesMethods;  

public Authorizer(){};
public Authorizer(Authentificater authent, Accounter accounter, Map<String, Set<String>> rulesMethods) {
	super();
	this.authent = authent;
	this.accounter = accounter;
	this.rulesMethods = rulesMethods;
}


//joinPoint.getTarget() - reference to invocation object
//check what's role for the invocation object
//check if the invocated method is permitted
//if yes to proceed if no to throw SecurityException
//accounting call or reject
public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable{
	Object obj = joinPoint.getTarget();
	String role = authent.getRole(obj);
	if (rulesMethods.containsKey(role)){
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		Set<String> methods = rulesMethods.get(role);
		if (methods.contains(methodName)){
			accounter.methodCallReject(methodName, true);
			return joinPoint.proceed();
		}
		else {
			accounter.methodCallReject(methodName, false);
			throw new IllegalArgumentException("403 Authorization failed!");
		}
	}
	else throw new IllegalArgumentException("401 Authentification failed!");
}
}
