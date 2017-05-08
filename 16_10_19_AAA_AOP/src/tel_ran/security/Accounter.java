package tel_ran.security;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Accounter {
private String fileName; 
//key - method name. value - array of statistics //[0]- number of calls, [1] - number of rejects
private Map<String,int[]> callRejects = new HashMap<>();   

public Accounter(String fileName) {
	super();
	this.fileName = fileName;
}

//restore map from the file
public void restoreMap(){
	try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
	while(true){
		String line = reader.readLine();
		if (line==null)
			break;
		else {
			String args[] = line.split(" ");
			int[] res = new int[2];
			res[0] = Integer.parseInt(args[1]);
			res[1] = Integer.parseInt(args[2]);
			callRejects.put(args[0], res);
		}
	}
	}
	catch(Exception e){
		//System.out.println(e.getMessage());		
	}
}
//key - method name. value - array of statistics //[0]- number of calls, [1] - number of rejects
//save map into file
public void saveUpdatedMap(){	
	try (PrintWriter writer = new PrintWriter(fileName)){		
		for (Entry<String, int[]> entry : callRejects.entrySet()){
			int[] intRes=entry.getValue();
			String strRes = entry.getKey() + " " + intRes[0] + " " + intRes[1];
			writer.println(strRes);
		}
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}
}

public void methodCallReject(String methodName, boolean call){
	int [] res = callRejects.get(methodName);
	if (res==null){
		res = new int[2];
	}
	if (call==true)
		res[0]=res[0]+1;
	else res[1] = res[1]+1;
	callRejects.put(methodName, res);		
}
}
