package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.WEB_XML_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.WEB_XML_URL;

public class WebXmlAnnotationFactoryTest {

    @Test
    @SuppressWarnings("unchecked")
    public void No_web_xml_is_returned_if_none_is_configured() {

        // Given
        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.webXml()).thenReturn("");

        // When
        final URL actual = new WebXmlAnnotationFactory().create(configuration);

        // Then
        assertNull(actual);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Web_xml_are_returned_if_some_are_configured() {

        // Given
        final URL expected = WEB_XML_URL;

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.webXml()).thenReturn(WEB_XML_PATH);

        // When
        final URL actual = new WebXmlAnnotationFactory().create(configuration);

        // Then
        assertEquals(expected, actual);
    }
}
