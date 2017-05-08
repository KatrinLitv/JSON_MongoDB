import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//might be unique or not
@Retention(RetentionPolicy.RUNTIME)
public @interface Index {
//without -default "false"- compilator will insist on setting default option
String unique() default "false";

}
