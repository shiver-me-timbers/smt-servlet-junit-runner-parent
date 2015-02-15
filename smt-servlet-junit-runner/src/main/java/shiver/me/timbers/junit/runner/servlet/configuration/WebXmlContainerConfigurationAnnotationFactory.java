package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.NullEmptyFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.inject.ClassAnnotationExtractor;

import java.net.URL;

/**
 * @author Karl Bennett
 */
public class WebXmlContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, URL> implements WebXmlFactory {

    private final Logger log = LoggerFactory.getLogger(WebXmlContainerConfigurationAnnotationFactory.class);

    public WebXmlContainerConfigurationAnnotationFactory() {
        super(
                new ClassAnnotationExtractor<>(ContainerConfiguration.class),
                new NullEmptyFactory<URL>(),
                new WebXmlAnnotationFactory()
        );

        log.debug("Constructed");
    }
}
