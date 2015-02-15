/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet.annotation;

import shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation can be used to configure a tests servlet container. This can be done through code or XML.
 * <p/>
 * To manually configure the container with code the annotation can be applied at the class level with an implementation
 * of the {@link shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration} interface. This
 * implementation should be generically typed to the containers configuration class.
 * <p/>
 * <pre>
 * {@literal @}ContainerConfiguration(CustomContainerConfiguration.class)
 *  class SomeTest {
 *  }</pre>
 *
 * Or apply it to a method that has the servlet containers configuration class as it's first argument.<br/><br/>
 *
 * <pre>
 *  class SomeTest {
 *     {@literal @}ContainerConfiguration
 *      public void config(Tomcat tomcat) {
 *          // Configuration goes here.
 *      }
 *  }</pre>
 *
 * By default the server will be started on a random port. The number of this port can be accessed with the {@link Port}
 * annotation.<br/>
 * It is also possible to manually set the port.<br/><br/>
 *
 * <pre>
 * {@literal @}ContainerConfiguration(port = 8080)
 *  class SomeTest {
 *  }</pre>
 *
 * The {@link Servlet}s that should be loaded into the container for the test can be set by listing them at the class
 * level.<br/><br/>
 *
 * <pre>
 * {@literal @}ContainerConfiguration(servlets = {ServletOne.class, ServletTwo.class, ServletThree.class})
 *  class SomeTest {
 *  }</pre>
 *
 * {@link Filter}s can be set the same way.<br/><br/>
 *
 * <pre>
 * {@literal @}ContainerConfiguration(filters = {FilterOne.class, FilterTwo.class, FilterThree.class})
 *  class SomeTest {
 *  }</pre>
 *
 * Alternatively a list of packages can be supplied that will be scanned for any {@link Servlet}s and {@link Filter}s.<br/><br/>
 *
 * <pre>
 * {@literal @}ContainerConfiguration(packages = {"package.one", "package.two", "package.three"})
 *  class SomeTest {
 *  }</pre>
 *
 * Lastly the path to a {@code web.xml} file can be provided which will then be used by the container. This path should
 * be relative to the root of the class path.<br/><br/>
 *
 * <pre>
 * {@literal @}ContainerConfiguration(webXml = "path/to/web.xml")
 *  class SomeTest {
 *  }</pre>
 *
 * Any {@code Servlet} or {@code Filter} classes that are to be loaded, but don't have any explicit configuration
 * through annotations or XML will be given the minimum default configuration of:<br/>
 * {@code name}/{@code filterName}: the simple class name<br/>
 * {@code urlPatterns}: /[the simple class name]<br/><br/>
 *
 * It is also possible to set some explicit configuration for the {@code Servlet} or {@code Filter} just for the test.
 * This will override any other configuration.<br/><br/>
 *
 * <pre>
 * {@literal @}ContainerConfiguration(
 *      servletConfigurations = {
 *         {@literal @}ServletConfiguration(
 *              configuration ={@literal @}WebServlet(
 *                  name = "servlet-one",
 *                  value = "/one"
 *              )
 *              servlet = ServletOne.class
 *          )
 *      }
 *  )
 *  class SomeTest {
 *  }</pre>
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
    Class<? extends shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration> value() default NullContainerConfiguration.class;

    /**
     * The {@link Servlet}s that should be loaded for this test.
     */
    Class<? extends Servlet>[] servlets() default {};

    /**
     * The explicitly configured {@link Servlet}s that should be loaded for this test.
     */
    ServletConfiguration[] servletConfigurations() default {};

    /**
     * The {@link Filter}s that should be loaded for this test.
     */
    Class<? extends Filter>[] filters() default {};

    /**
     * The explicitly configured {@link Filter}s that should be loaded for this test.
     */
    FilterConfiguration[] filterConfigurations() default {};

    /**
     * The packages that should be scanned for {@link Servlet} classes.
     */
    String[] packages() default {};

    /**
     * The path to the {@code web.xml} file that is relative to the root of the class path.
     */
    String webXml() default "";
}
