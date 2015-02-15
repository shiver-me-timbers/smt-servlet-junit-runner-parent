package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class CompositeContainerConfigurationAnnotationFactory<T>
        implements AnnotationFactory<ContainerConfiguration, T> {

    private final Logger log = LoggerFactory.getLogger(CompositeContainerConfigurationAnnotationFactory.class);

    private final Factory<List<T>, T> appender;
    private final AnnotationFactory<ContainerConfiguration, T>[] factories;

    @SafeVarargs
    public CompositeContainerConfigurationAnnotationFactory(Factory<List<T>, T> appender,
                                                            AnnotationFactory<ContainerConfiguration, T>... factories) {
        this.appender = appender;
        this.factories = factories;

        log.debug("Constructed");
    }

    @Override
    public T create(ContainerConfiguration configuration) {

        final List<T> list = new ArrayList<>();

        log.debug("Executing annotation factories");
        for (AnnotationFactory<ContainerConfiguration, T> factory : factories) {
            log.debug("Executing {}", factory);
            list.add(factory.create(configuration));
        }

        log.debug("Appending factory outputs");
        return appender.create(list);
    }
}
