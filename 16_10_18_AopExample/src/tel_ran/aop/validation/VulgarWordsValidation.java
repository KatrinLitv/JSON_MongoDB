package tel_ran.aop.validation;

import org.aspectj.lang.ProceedingJoinPoint;

public class VulgarWordsValidation {
//Метод, которому Аспект передает управление.
public Object validate(ProceedingJoinPoint joinPoint) throws Throwable{
	Object [] args = joinPoint.getArgs();
	if (args!=null) {
		for (int i=0 ; i<args.length ; i++) {
			if (args[i] instanceof String) {
				String str = (String) args[i];				
				args[i] = str.replaceAll("(?i)hello","****");
//				String str = (String) obj;
//				if (str.toLowerCase().contains("hello"))
//					throw new IllegalArgumentException("we are tired from 'hello'");
			}
		} 
	}
	return joinPoint.proceed(args);	
}
}
