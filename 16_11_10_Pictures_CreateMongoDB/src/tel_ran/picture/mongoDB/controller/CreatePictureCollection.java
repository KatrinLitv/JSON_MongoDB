package tel_ran.picture.mongoDB.controller;

import java.io.*;
import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tel_ran.picture.mongoDB.dao.PictureMongoDB;

public class CreatePictureCollection {
public static void main(String[] args) throws Exception {
	AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");	
	PictureMongoDB pictureMongo = ctx.getBean(PictureMongoDB.class);
	
	BufferedReader reader = new BufferedReader(new FileReader("images.txt"));
	List<String> urls = new LinkedList<>();
	while (true){
		String line = reader.readLine();
		if (line==null)
			break;
		System.out.println(line);
		urls.add(line);
	}
	pictureMongo.saveMany(urls);
	reader.close();
	ctx.close();
	}
}
