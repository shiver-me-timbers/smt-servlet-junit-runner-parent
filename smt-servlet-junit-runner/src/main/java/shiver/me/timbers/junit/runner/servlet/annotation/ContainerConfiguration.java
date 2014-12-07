package shiver.me.timbers.junit.runner.servlet.annotation;

import shiver.me.timbers.junit.runner.servlet.config.NullContainerConfiguration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation can be used to configure the servlet container. This can be done through code or XML.
 *
 * To manually configure the container with code the annotation can be applied at the class level with an implementation
 * of the {@link shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration} interface. This implementation
 * should be generically typed to the containers configuration class.
 *
 * <pre>
 * {@code
 *  &#64;ContainerConfiguration(CustomContainerConfiguration.class)
 *  class SomeTest {
 *  }
 * }
 * </pre>
 *
 * Or apply it to a method that has the servlet containers configuration class as it's first argument.
 *
 * <pre>
 * {@code
 *  class SomeTest {
 *      &#64;ContainerConfiguration
 *      public void config(Tomcat tomcat) {
 *          // Configuration goes here.
 *      }
 *  }
 * }
 * </pre>
 *
 * The {@link Servlet}s that should be loaded into the container for the test class can be set by listing them at the
 * class level.
 *
 * <pre>
 * {@code
 *  &#64;ContainerConfiguration(servlets = {ServletOne.class, ServletTwo.class, ServletThree.class})
 *  class SomeTest {
 *  }
 * }
 * </pre>
 *
 * {@link Filter}s can be set the same way.
 *
 * <pre>
 * {@code
 *  &#64;ContainerConfiguration(filters = {FilterOne.class, FilterTwo.class, FilterThree.class})
 *  class SomeTest {
 *  }
 * }
 * </pre>
 *
 * Alternatively a list of packages can be supplied that will be scanned for any {@link Servlet}s and {@link Filter}s.
 *
 * <pre>
 * {@code
 *  &#64;ContainerConfiguration(packages = {"one.package", "two.package", "three.package"})
 *  class SomeTest {
 *  }
 * }
 * </pre>
 *
 * Lastly the path to a {@code web.xml} can be provided which will then be used by the container. This path should be
 * relative to the root of the class path.
 *
 * <pre>
 * {@code
 *  &#64;ContainerConfiguration(webXml = "path/to/web.xml")
 *  class SomeTest {
 *  }
 * }
 * </pre>
 *
 * @author Karl Bennett
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface ContainerConfiguration {

    int port() default -1;

    /**
     * The class of the container configuration that should be used with this test.
     */
    Class<? extends shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration> value() default NullContainerConfiguration.class;

    /**
     * The {@link Servlet}s that should be loaded for this test.
     */
    Class<? extends Servlet>[] servlets() default {};

    /**
     * The {@link Filter}s that should be loaded for this test.
     */
    Class<? extends Filter>[] filters() default {};

    /**
     * The packages that should be scanned for {@link Servlet} classes.
     */
    String[] packages() default {};

    /**
     * The path to the {@code web.xml} file that is relative to the root of the class path.
     */
    String webXml() default "";
}
