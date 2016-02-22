package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
/**
 * Describes the format of the Manufacturer annotation added to the test Robotium
 * @author Ana Gissel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
public @interface Manufacturer {

	String manufacturer();
}
