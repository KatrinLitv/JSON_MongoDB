package tel_ran.mongo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;

import tel_ran.databases.mongo.MongoConnection;
import tel_ran.mongo.annotations.Id;
import tel_ran.mongo.annotations.Index;

public class MongoTemplate<T,ID> {
	private MongoCollection<Document> collection;
	private static final String TYPE = "_class";
	static ObjectMapper mapper = new ObjectMapper();
	
	public MongoTemplate(String uriStr, String databaseName, String collectionName) {
		super();
		MongoConnection mongoConnection = MongoConnection.getMongoCollection(uriStr, databaseName);
		collection = mongoConnection.getDataBase().getCollection(collectionName);	
	}
	
	public void saveMany(List<T> objects){
		collection.insertMany(getListDocuments(objects));
	}
	
	private List<Document> getListDocuments(List<T> objects) {
		List<Document> res = new LinkedList<>();		
		for (T obj : objects){
			String keyField = getKeyField(obj);
			res.add(getDocument(obj,keyField));
		}
		return res;
	}

	public void saveOne( T obj){
		String keyField = getKeyField(obj);
		collection.insertOne(getDocument(obj,keyField));		
	}

	@SuppressWarnings("unchecked")
	private String getKeyField(T obj) {
		String res=null;
		Class<T> clazz = (Class<T>) obj.getClass();
		
		HashSet<Field> fieldsAnn = new HashSet<>();
		getAllFields(clazz,fieldsAnn);
		for (Field field : fieldsAnn) {	
			if (field.isAnnotationPresent(Id.class)){
				res = field.getName();				
			}
			else if (field.isAnnotationPresent(Index.class)){
				boolean unique = field.getAnnotation(Index.class).unique();
				IndexOptions indexOptions = new IndexOptions().unique(unique);
				Document key = new Document(field.getName(), Integer.valueOf(1));
				collection.createIndex(key, indexOptions);
			} 
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	private Document getDocument(T obj, String keyField) {
		Map<String, Object> map = new LinkedHashMap<>();
		try {
			map = mapper.readValue(mapper.writeValueAsString(obj), HashMap.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Class<T> clazz= (Class<T>) obj.getClass();
		map.put(TYPE,clazz.getName());
		if (keyField!=null){
			Object tmp = map.remove(keyField);
			map.put("_id", tmp);
		}
		return new Document(map);
	}

	public Object findOne(ID id){
		Document query = new Document("_id",id);
		FindIterable<Document> resIterable = collection.find(query);
		if (resIterable==null)
			return null;
		Document resDocument = resIterable.first();
		if (resDocument==null)
			return null;		
		return getFromDocument(resDocument);
	}

	@SuppressWarnings("unchecked")
	private T getFromDocument(Document resDocument) {
		T res=null;
		try {
			String type= (String)resDocument.get(TYPE);
			Class<T> c = (Class<T>) Class.forName(type); 	
			res = c.newInstance();

			//рекурсия??			
			HashSet<Field> fields = new HashSet<>();
			getAllFields(c,fields);
			//
			for (Field field : fields) {				
				String fieldName = field.getName();
				if (field.isAnnotationPresent(Id.class))
					field.set(res, resDocument.get("_id"));
				else 
					field.set(res, resDocument.get(fieldName)); 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	

	@SuppressWarnings("unchecked")
	private void getAllFields(Class<T> c, HashSet<Field> fields) {
		Field declFields[] = c.getDeclaredFields();
		fields.addAll(Arrays.asList(declFields));
		if (c.getSuperclass()==null)
			return;
		else getAllFields((Class<T>) c.getSuperclass(),fields);
	}

	public Iterable<T> findMany(Document query){
		FindIterable<Document> resIterable = collection.find(query);
		if (resIterable==null)
			return null;
		return getObjects(resIterable);
	}

	private Iterable<T> getObjects(FindIterable<Document> resIterable) {
		ArrayList<T> res = new ArrayList<>();
		for (Document doc : resIterable){
			if (doc!=null)
				res.add(getFromDocument(doc));
		}			
		return res;
	}


}
