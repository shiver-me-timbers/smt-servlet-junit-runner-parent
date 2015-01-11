package shiver.me.timbers.junit.runner.servlet.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation can be used to inject a dynamically allocated port by applying it to an {@code Integer/int} field
 * within a test.
 * <p/>
 * <pre>
 * {@code
 *  class SomeOtherTest {
 *     {@literal @}Port
 *      private int port;
 *  }
 * }
 * </pre>
 *
 * @author Karl Bennett
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Port {
}
