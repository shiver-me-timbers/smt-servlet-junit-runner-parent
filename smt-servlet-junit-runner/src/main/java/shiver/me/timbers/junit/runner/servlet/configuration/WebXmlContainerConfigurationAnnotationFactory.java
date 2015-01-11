package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.NullEmptyFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.net.URL;

/**
 * @author Karl Bennett
 */
public class WebXmlContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, URL> implements WebXmlFactory {

    public WebXmlContainerConfigurationAnnotationFactory() {
        super(ContainerConfiguration.class, new NullEmptyFactory<URL>(), new WebXmlAnnotationFactory());
    }
}
