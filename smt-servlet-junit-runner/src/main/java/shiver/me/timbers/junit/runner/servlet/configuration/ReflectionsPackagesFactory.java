package shiver.me.timbers.junit.runner.servlet.configuration;

import org.reflections.Reflections;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Packages;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karl Bennett
 */
public class ReflectionsPackagesFactory<T> implements PackagesFactory<T> {

    private static final String JAVAX_SERVLET = "javax.servlet";
    private final Factory<List<Class<? extends Servlet>>, T> typeConverter;

    public ReflectionsPackagesFactory(Factory<List<Class<? extends Servlet>>, T> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public T create(Packages packages) {

        final List<Class<? extends Servlet>> servletDetails = new ArrayList<>();

        for (String pkg : packages) {

            final Reflections reflections = new Reflections(pkg, JAVAX_SERVLET);

            final Set<Class<? extends Servlet>> types = filterJavaxServlet(reflections.getSubTypesOf(Servlet.class));

            servletDetails.addAll(types);
        }

        return typeConverter.create(servletDetails);
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
}
