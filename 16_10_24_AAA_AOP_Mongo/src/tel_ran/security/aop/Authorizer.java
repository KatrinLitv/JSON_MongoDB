package tel_ran.security.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import tel_ran.security.Accounter;
import tel_ran.security.Authentificater;
import tel_ran.security.Authorized;

import java.lang.reflect.Method;
import java.util.*;

public class Authorizer {
Authentificater authent;
Accounter accounter;
//key - role name, value - set of the permitted method names
//Map<String,Set<String>> rulesMethods;  

public Authorizer(){};
public Authorizer(Authentificater authent, Accounter accounter, Map<String, Set<String>> rulesMethods) {
	super();
	this.authent = authent;
	this.accounter = accounter;
	//this.rulesMethods = rulesMethods;
}


//joinPoint.getTarget() - reference to invocation object
//check what's role for the invocation object
//check if the invocated method is permitted
//if yes to proceed if no to throw SecurityException
//accounting call or reject
public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable{
	Object obj = joinPoint.getTarget();
	String role = authent.getRole(obj);
	
	Signature signature = joinPoint.getSignature();
	String methodName = signature.getName();
	
	Method method = ((MethodSignature)joinPoint.getSignature()).getMethod(); 
	String roles[];
	if (method.isAnnotationPresent(Authorized.class)){
		roles = method.getAnnotation(Authorized.class).roles();
		if ((roles!=null)&&(Arrays.asList(roles).contains(role))){
			accounter.methodCallReject(methodName, true);
			return joinPoint.proceed();
		}
		else {
			accounter.methodCallReject(methodName, false);
			throw new IllegalArgumentException("403 Authorization failed!");
		}	
	}
	else {
		accounter.methodCallReject(methodName, true);
		return joinPoint.proceed();
	}
			
//		}
//		else throw new IllegalArgumentException("401 Authentification failed!");
//		}
}
}
