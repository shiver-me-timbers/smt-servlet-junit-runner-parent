package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.net.URL;

/**
 * @author Karl Bennett
 */
public class WebXmlAnnotationFactory implements AnnotationFactory<ContainerConfiguration, URL> {

    @Override
    public URL create(ContainerConfiguration input) {

        final String webXml = input.webXml();

        if (webXml.isEmpty()) {
            return null;
        }

        return getResource(webXml);
    }

    private static URL getResource(String webXml) {
        return Thread.currentThread().getContextClassLoader().getResource(webXml);
    }
}
