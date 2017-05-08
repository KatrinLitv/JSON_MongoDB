package tel_ran.security.admin;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.security.Authentificater;
import tel_ran.security.UserAccount;

public class AdminAppl {

	public static void main(String[] args) {
		System.out.println("Enter new user name:");		
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		System.out.println("Enter new user password:");
		String password = in.nextLine();
		System.out.println("Enter role:");
		String role = in.nextLine();
		UserAccount userAccount = new UserAccount(name, password, role);
		//System.out.println(userAccount);
		
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beansAOP.xml");
		Authentificater auth = ctx.getBean(Authentificater.class);
		auth.addUserAccount(userAccount);
		ctx.close();
	}

}
