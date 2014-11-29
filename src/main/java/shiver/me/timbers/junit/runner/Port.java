package shiver.me.timbers.junit.runner;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation can be used to manually set the port servlet containers port to a static number by applying it at the
 * class level.
 *
 * <pre>
 * {@code
 *  @Port(8080)
 *  class SomeTest {
 *  }
 * }
 * </pre>
 *
 * Or it can be used to inject a dynamically allocated port by applying it to an {@code Integer/int} field within a
 * test.
 *
 * <pre>
 * {@code
 *  class SomeOtherTest {
 *      @Port
 *      private int port;
 *  }
 * }
 * </pre>
 *
 * @author Karl Bennett
 */
@Target({FIELD, TYPE})
@Retention(RUNTIME)
public @interface Port {

    /**
     * @return the desired port number for the servlet container instance. It is -1 by default which will cause a random
     *         port to be allocated dynamically.
     */
    int value() default -1;
}
