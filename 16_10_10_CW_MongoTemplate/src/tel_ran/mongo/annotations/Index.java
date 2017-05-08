package tel_ran.mongo.annotations;

import java.lang.annotation.*;

@Retention (RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
@Inherited
public @interface Index {
boolean unique() default false;
}
