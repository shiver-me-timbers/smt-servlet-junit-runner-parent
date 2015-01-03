package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class CompositeContainerConfigurationAnnotationFactory<T>
        implements AnnotationFactory<ContainerConfiguration, T> {

    private final Factory<List<T>, T> appender;
    private final AnnotationFactory<ContainerConfiguration, T>[] factories;

    @SafeVarargs
    public CompositeContainerConfigurationAnnotationFactory(Factory<List<T>, T> appender,
                                                            AnnotationFactory<ContainerConfiguration, T>... factories) {
        this.appender = appender;
        this.factories = factories;
    }

    @Override
    public T create(ContainerConfiguration configuration) {

        final List<T> list = new ArrayList<>();

        for (AnnotationFactory<ContainerConfiguration, T> factory : factories) {

            list.add(factory.create(configuration));
        }

        return appender.create(list);
    }
}
