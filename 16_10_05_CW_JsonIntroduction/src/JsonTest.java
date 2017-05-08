import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	private static final String TYPE = "_class";
	static ObjectMapper mapper = new ObjectMapper();
	
public static void main(String[] args) throws Exception {
//		int []ar = {1,4,-30,20};
//		String json=mapper.writeValueAsString(ar);
//		System.out.println(json);
//		// now fom string to array
//		int ar1[] = mapper.readValue(json, int[].class);
//		System.out.println(ar1.length + ";  "+(ar1[0]+ar1[1]));
	
	
//	X x = new X();
//	x.xarf = new float [] {2.4f, 3.7f} ;
//	x.xi = 10;
//	x.xs="Hello";
//	
//	String  json=mapper.writeValueAsString(x);
//	System.out.println(json);
	
	
	
//	X ar[] = {new X(10, new float[]{2.3f,6,7.8f},"Hello!!!")};
//	String  json=mapper.writeValueAsString(ar);
//	System.out.println(json);
//	X ar1[] = mapper.readValue(json, X[].class);
//	System.out.println(Arrays.deepToString(ar1));
	
//	X ar1[] = mapper.readValue("[{\"xi\":10,\"xarf\":[2.3,6.0,7.8],\"xs\":\"Hello!!!\"}]", X[].class);
//	System.out.println(Arrays.deepToString(ar1));
	
	////конфигурация: если есть неизвестные поля - не надо падать
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	
	//!сериализация
	X ar[] = {new X(10, new float[]{2.3f,6,7.8f},"Hello!!!"), 
			new Y(70, new float[]{4.5f, 4}, "xs", "ys", 11)};
	List<Map<String,Object>> listMaps = getListMaps(ar);
	String  json=mapper.writeValueAsString(listMaps);
	System.out.println(json);
	
	////String  json=mapper.writeValueAsString(ar);
	
	//! десериализация
	
	List<Map<String,Object>> listMapsJson = mapper.readValue(json, List.class);
	X []ar1 = getArrayX(listMapsJson);
	System.out.println(Arrays.deepToString(ar1));
	
//	X ar1[] = mapper.readValue(json, X[].class);
//	System.out.println(Arrays.deepToString(ar1));
	}

private static X[] getArrayX(List<Map<String, Object>> listMapsJson) throws Exception {
	X [] res = new X[listMapsJson.size()];
	int ind=0;
	for (Map<String, Object> map : listMapsJson){
		res[ind++]=getX(map);		
	}
	return res;
}

private static X getX(Map<String, Object> map) throws Exception {
	String json =mapper.writeValueAsString(map);
	return (X) mapper.readValue(json,Class.forName((String) map.get(TYPE)));
}

private static List<Map<String, Object>> getListMaps(X[] ar) throws Exception {
	List<Map<String,Object>>  res = new ArrayList<>();
	for (X x: ar){
		res.add(getMap(x));
	}
	return res;
}

private static Map<String, Object> getMap(X x) throws Exception {	
	String json=mapper.writeValueAsString(x);
	Map<String,Object> res = mapper.readValue(json, Map.class);
	res.put(TYPE,x.getClass().getName());
	return res;
}

}
