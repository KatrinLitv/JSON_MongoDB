package tel_ran.aop.benchmarking;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class PerformanceTest {
public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable{
	Signature signature = joinPoint.getSignature();
	String methodName = signature.getName();
	long time1=System.currentTimeMillis();
	Object res=joinPoint.proceed();
	long time2=System.currentTimeMillis();
	System.out.println("method name: "+ methodName+"; running time:"+ (time2-time1));
	return res;	
}
}
