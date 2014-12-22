package shiver.me.timbers.junit.runner.servlet;

import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.net.URL;
import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class AnnotationPackagesFactory implements PackagesFactory {

    @Override
    public Packages create(Object target) {

        final Class<?> type = target.getClass();

        final ContainerConfiguration configuration = type.getAnnotation(ContainerConfiguration.class);

        if (null == configuration) {
            return new SettablePackages(new ArrayList<URL>());
        }

        return new SettablePackages(configuration.packages());
    }
}
