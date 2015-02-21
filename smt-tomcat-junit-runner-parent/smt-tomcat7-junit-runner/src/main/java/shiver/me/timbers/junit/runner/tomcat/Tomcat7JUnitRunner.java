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

package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Host;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.JarScanner;
import org.junit.runners.model.InitializationError;

import javax.servlet.ServletException;

/**
 * This JUnit test runner will start up an Embedded Tomcat instance for the related test. The
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration} annotation can then be used to
 * configure the Tomcat instance.
 *
 * @author Karl Bennett
 */
public class Tomcat7JUnitRunner extends TomcatJUnitRunner<Tomcat, Host, JarScanner, WrappedFilterDef, WrappedFilterMap> {

    public Tomcat7JUnitRunner(Class test) throws InitializationError, ServletException {
        super(new WrappedTomcat7(), new NullJarScanner(), test);
    }
}
