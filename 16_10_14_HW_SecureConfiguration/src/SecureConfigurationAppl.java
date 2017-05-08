import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SecureConfigurationAppl {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		SecureConfiguration userMap = (SecureConfiguration) ctx.getBean("userMap");
		System.out.println(logIn(userMap));
		//userMap.displayUsers();		
		ctx.close();
		
	}

	private static String logIn(SecureConfiguration userMap) {		
		System.out.println("Enter user name:");		
		Scanner in = new Scanner(System.in);
		String nameIn = in.nextLine();
		System.out.println("Enter user password:");
		String passIn = in.nextLine();
		String res = "Access denied!!!";
		if (userMap.containsUser(nameIn, passIn))
			res=nameIn+"! Welcome!!!";		
		return res;
	}

}
