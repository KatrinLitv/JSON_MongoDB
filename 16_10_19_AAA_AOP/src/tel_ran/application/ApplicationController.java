package tel_ran.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationController {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beansAOP.xml");
		ApplicationClass appl = (ApplicationClass) ctx.getBean("appl");
		appl.login("admin", "1234");
		try {
			appl.get1();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			appl.set1();
			appl.set1();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ctx.close();
	}

}
