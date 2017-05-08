import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationsTestAppl {

	public static void main(String[] args) {
		X x;
		Class clazz = X.class;
		// for class
		/*Annotation ann=clazz.getAnnotation(Id.class);
		System.out.println(ann);*/
		Field fields[] = clazz.getDeclaredFields();
		for (Field field : fields){
			if (field.isAnnotationPresent(Id.class))
				System.out.println(field.getName());
			else if(field.isAnnotationPresent(Index.class)){
				String name = field.getName();
				String unique = field.getAnnotation(Index.class).unique();
				System.out.println(name + " " + unique);
			}				
		}
	}

}
