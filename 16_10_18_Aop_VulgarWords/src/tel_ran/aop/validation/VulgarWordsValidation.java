package tel_ran.aop.validation;

import org.aspectj.lang.ProceedingJoinPoint;

public class VulgarWordsValidation {
String [] words;
String strNew=null;

public VulgarWordsValidation(){};
public VulgarWordsValidation(String[] words, String strNew) {
	super();
	this.words = words;
	this.strNew = strNew;
}

//Метод, которому Аспект передает управление.
public Object validate(ProceedingJoinPoint joinPoint) throws Throwable{
	Object [] args = joinPoint.getArgs();
	if (args!=null) 
		for (int i=0 ; i<args.length ; i++) {
			if (args[i] instanceof String) 
				for (int j=0; j<words.length ; j++){
					String str = (String) args[i];
					if (str.toLowerCase().contains(words[j].toLowerCase())){
						if (strNew==null)
							throw new IllegalArgumentException("we are tired from " +words[j]);
						else args[i] = ((String)args[i]).replaceAll("(?i)"+words[j],strNew);	
					}
				}
		} 
	return joinPoint.proceed(args);	
}

}
