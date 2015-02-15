package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.net.URL;

/**
 * @author Karl Bennett
 */
public class WebXmlAnnotationFactory implements AnnotationFactory<ContainerConfiguration, URL> {

    private final Logger log = LoggerFactory.getLogger(WebXmlAnnotationFactory.class);

    @Override
    public URL create(ContainerConfiguration input) {

        final String webXml = input.webXml();

        if (webXml.isEmpty()) {
            log.debug("No web.xml configured");
            return null;
        }
        log.debug("Getting resource URL for {}", webXml);
        return getResource(webXml);
    }

    private static URL getResource(String webXml) {
        return Thread.currentThread().getContextClassLoader().getResource(webXml);
    }
}
