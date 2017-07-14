package example.annotations;

/**
 * Created by ${xzl} on 2017/7/13.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyAnnotation {
    String value() default "HelloWorld";
}
