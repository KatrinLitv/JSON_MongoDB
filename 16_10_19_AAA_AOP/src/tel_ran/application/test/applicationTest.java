package tel_ran.application.test;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.application.ApplicationClass;

public class applicationTest {
static AbstractApplicationContext ctx;
static ApplicationClass appl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new FileSystemXmlApplicationContext("beansAOP.xml");
		appl = (ApplicationClass) ctx.getBean("appl");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ctx.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void applAdminTest() {
		assertFalse(tryMethod("set1"));
		appl.login("admin", "1234");
		assertTrue(tryMethod("set1"));
		assertTrue(tryMethod("set2"));
		assertTrue(tryMethod("get1"));
	}

	@Test
	public void applUserTest() {
		appl.login("user2", "2345");
		assertFalse(tryMethod("set1"));
		assertTrue(tryMethod("set2"));
	}
	
	@Test
	public void applWrongUserTest() {
		appl.login("user1", "23456789");
		assertFalse(tryMethod("set1"));
		assertFalse(tryMethod("set2"));
	}
	
	private boolean tryMethod(String methodStr) {
		boolean res=true;
		try {			
			Method method = appl.getClass().getDeclaredMethod(methodStr); 
			method.invoke(appl);
		} catch (Exception e) {
			res =false;
		}
		return res;
	}

}
