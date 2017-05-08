package tel_ran.chat.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.chat.ChatRoom;

public class ChatAppl {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beansAOP.xml");
		ChatRoom chatRoom = (ChatRoom) ctx.getBean("chat");
		try {
			chatRoom.saySomething("word1 Vasya!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			chatRoom.replySomething("hello!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ctx.close();
	}

}
