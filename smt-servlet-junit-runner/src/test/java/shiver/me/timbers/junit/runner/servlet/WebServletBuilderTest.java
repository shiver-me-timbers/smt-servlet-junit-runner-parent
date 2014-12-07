package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WebServletBuilderTest {

    @Test
    public void A_web_servlet_can_be_built() {
        // Given
        final String name = "name";
        final String value = "value";
        final String urlPattern = "urlPatterns";
        final int loadOnStartup = 1;
        final WebInitParam initParams = mock(WebInitParam.class);
        final boolean asyncSupported = true;
        final String smallIcon = "smallIcon";
        final String largeIcon = "largeIcon";
        final String description = "description";
        final String displayName = "displayName";

        // When
        final WebServlet actual = WebServletBuilder.create()
                .withName(name)
                .withValue(value)
                .withUrlPatterns(urlPattern)
                .withLoadOnStartup(loadOnStartup)
                .withInitParams(initParams)
                .withAsyncSupported(asyncSupported)
                .withSmallIcon(smallIcon)
                .withLargeIcon(largeIcon)
                .withDescription(description)
                .withDisplayName(displayName)
                .build();

        // Then
        assertEquals(name, actual.name());
        assertArrayEquals(new String[]{value}, actual.value());
        assertArrayEquals(new String[]{urlPattern}, actual.urlPatterns());
        assertEquals(loadOnStartup, actual.loadOnStartup());
        assertArrayEquals(new WebInitParam[]{initParams}, actual.initParams());
        assertEquals(asyncSupported, actual.asyncSupported());
        assertEquals(smallIcon, actual.smallIcon());
        assertEquals(largeIcon, actual.largeIcon());
        assertEquals(description, actual.description());
        assertEquals(displayName, actual.displayName());
        assertEquals(WebServlet.class, actual.annotationType());
    }

    @Test
    public void A_default_web_servlet_can_be_built() {

        // When
        final WebServlet actual = WebServletBuilder.create().build();

        // Then
        assertThat(actual.name(), isEmptyString());
        assertArrayEquals(new String[0], actual.value());
        assertArrayEquals(new String[0], actual.urlPatterns());
        assertEquals(-1, actual.loadOnStartup());
        assertArrayEquals(new WebInitParam[0], actual.initParams());
        assertFalse(actual.asyncSupported());
        assertThat(actual.smallIcon(), isEmptyString());
        assertThat(actual.largeIcon(), isEmptyString());
        assertThat(actual.description(), isEmptyString());
        assertThat(actual.displayName(), isEmptyString());
        assertEquals(WebServlet.class, actual.annotationType());
    }
}
