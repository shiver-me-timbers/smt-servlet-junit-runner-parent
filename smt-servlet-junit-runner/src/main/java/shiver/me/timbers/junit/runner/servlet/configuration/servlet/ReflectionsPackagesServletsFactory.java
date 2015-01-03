package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.reflections.Reflections;
import shiver.me.timbers.junit.runner.servlet.Packages;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karl Bennett
 */
public class ReflectionsPackagesServletsFactory implements PackagesServletsFactory {

    private static final String JAVAX_SERVLET = "javax.servlet";

    @Override
    public Servlets create(Packages packages) {

        final List<ServletDetail> servletDetails = new ArrayList<>();

        for (String pkg : packages) {

            final Reflections reflections = new Reflections(pkg, JAVAX_SERVLET);

            final Set<Class<? extends Servlet>> types = filterJavaxServlet(reflections.getSubTypesOf(Servlet.class));

            servletDetails.addAll(toServletDetails(types));
        }

        return new SettableServlets(servletDetails);
    }

    private static Set<Class<? extends Servlet>> filterJavaxServlet(Set<Class<? extends Servlet>> types) {

        final Set<Class<? extends Servlet>> filteredTypes = new HashSet<>();

        for (Class<? extends Servlet> type : types) {
            if (!type.getName().startsWith(JAVAX_SERVLET)) {
                filteredTypes.add(type);
            }
        }

        return filteredTypes;
    }

    private static Collection<? extends ServletDetail> toServletDetails(Set<Class<? extends Servlet>> types) {

        final List<ServletDetail> servletDetails = new ArrayList<>();

        for (Class<? extends Servlet> type : types) {
            servletDetails.add(new ServletDetail(type));
        }

        return servletDetails;
    }
}
