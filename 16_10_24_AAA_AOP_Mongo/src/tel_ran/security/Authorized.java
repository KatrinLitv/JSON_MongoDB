package tel_ran.security;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
public @interface Authorized {
String [] roles() default {"admin"};
}
