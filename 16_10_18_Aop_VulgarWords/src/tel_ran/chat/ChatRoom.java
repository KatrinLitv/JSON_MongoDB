package tel_ran.chat;

public class ChatRoom {
public void saySomething(String message){
	System.out.println(message + " has been said");
}

public void replySomething(String message) throws InterruptedException{
	Thread.sleep(1000);
	System.out.println(message + " has been replied");
	
}
}
