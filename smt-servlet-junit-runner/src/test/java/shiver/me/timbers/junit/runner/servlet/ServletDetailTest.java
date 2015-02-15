/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.ASYNC_SUPPORT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.DESCRIPTION;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.DISPLAY_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.INIT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.INIT_PARAMS;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.LARGE_ICON;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.SMALL_ICON;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.URL_PATTERN;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.VALUE;

public class ServletDetailTest {

    private static final String NAME = "valid-servlet";
    private static final int LOAD_ON_STARTUP = 1;

    @Test
    public void An_annotated_servlet_class_can_be_used_for_creation() {

        // When
        final ServletDetail actual = new ServletDetail(AllAnnotatedServlet.class);

        // Then
        assertEquals(NAME, actual.getName());
        assertEquals(asList(VALUE), actual.getUrlPatterns());
        assertEquals(LOAD_ON_STARTUP, actual.loadOnStartup());
        assertEquals(INIT_PARAMS, actual.getInitParams());
        assertEquals(ASYNC_SUPPORT, actual.asyncSupported());
        assertEquals(SMALL_ICON, actual.getSmallIcon());
        assertEquals(LARGE_ICON, actual.getLargeIcon());
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(DISPLAY_NAME, actual.getDisplayName());
        assertServlet(actual, AllAnnotatedServlet.class);
    }

    @Test
    public void An_servlet_class_annotated_with_a_value_produces_the_correct_url_pattern() {

        // When
        final ServletDetail actual = new ServletDetail(AnnotatedWithValueServlet.class);

        // Then
        assertThat(actual.getName(), isEmptyString());
        assertEquals(asList(VALUE), actual.getUrlPatterns());
        assertDefaults(actual);
        assertServlet(actual, AnnotatedWithValueServlet.class);
    }

    @Test
    public void An_servlet_class_annotated_with_a_url_pattern_produces_the_correct_url_pattern() {

        // When
        final ServletDetail actual = new ServletDetail(AnnotatedWithUrlPatternsServlet.class);

        // Then
        assertThat(actual.getName(), isEmptyString());
        assertEquals(asList(URL_PATTERN), actual.getUrlPatterns());
        assertDefaults(actual);
        assertServlet(actual, AnnotatedWithUrlPatternsServlet.class);
    }

    @Test
    public void An_unannotated_servlet_class_can_be_used_for_creation() {

        // Given
        final String name = UnannotatedServlet.class.getSimpleName();
        final String urlPattern = "/" + name;

        // When
        final ServletDetail actual = new ServletDetail(UnannotatedServlet.class);

        // Then
        assertEquals(name, actual.getName());
        assertEquals(asList(urlPattern), actual.getUrlPatterns());
        assertDefaults(actual);
        assertServlet(actual, UnannotatedServlet.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_class_with_a_private_default_constructor_cannot_be_instantiated() {

        // When
        new ServletDetail(PrivateDefaultConstructorServlet.class).getServletInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_class_with_no_default_constructor_cannot_be_instantiated() {

        // When
        new ServletDetail(NoDefaultConstructorServlet.class).getServletInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_interface_cannot_be_instantiated() {

        // When
        new ServletDetail(Servlet.class).getServletInstance();
    }

    @Test
    public void A_servlet_detail_can_be_checked_for_equality() {

        final Equal<ServletDetail> equal = new Equal<ServletDetail>() {
            @Override
            public boolean equal(ServletDetail left, Object right) {
                return left.equals(right);
            }
        };

        assertEquality(equal);
    }

    @Test
    public void A_servlet_detail_can_be_generate_a_hash_code() {

        final Equal<ServletDetail> equal = new Equal<ServletDetail>() {
            @Override
            public boolean equal(ServletDetail left, Object right) {

                if (null == right) {
                    return false;
                }

                return left.hashCode() == right.hashCode();
            }
        };

        assertEquality(equal);
    }

    @Test
    public void A_servlet_detail_can_be_to_stringed() {

        final String expected = format(
                "ServletDetail {\n" +
                        "name='%s',\n" +
                        "urlPatterns=%s,\n" +
                        "loadOnStartup=%d,\n" +
                        "initParams=%s,\n" +
                        "asyncSupported=%s,\n" +
                        "smallIcon='%s',\n" +
                        "largeIcon='%s',\n" +
                        "description='%s',\n" +
                        "displayName='%s',\n" +
                        "servlet=%s\n" +
                        "}",
                NAME,
                asList(URL_PATTERN),
                LOAD_ON_STARTUP,
                INIT_PARAMS,
                ASYNC_SUPPORT,
                SMALL_ICON,
                LARGE_ICON,
                DESCRIPTION,
                DISPLAY_NAME,
                Servlet.class
        );

        final String actual = new ServletDetail(new ServletDetailBuilder().withAll().build()).toString();

        assertEquals(expected, actual);
    }

    private static void assertServlet(ServletDetail actual, Class<? extends Servlet> servlet) {
        assertEquals(servlet, actual.getServlet());
        assertThat(actual.getServletInstance(), instanceOf(servlet));
    }

    private static void assertDefaults(ServletDetail actual) {
        assertEquals(-1, actual.loadOnStartup());
        assertEquals(new HashMap<String, String>(), actual.getInitParams());
        assertFalse(actual.asyncSupported());
        assertThat(actual.getSmallIcon(), isEmptyString());
        assertThat(actual.getLargeIcon(), isEmptyString());
        assertThat(actual.getDescription(), isEmptyString());
        assertThat(actual.getDisplayName(), isEmptyString());
    }

    private static void assertEquality(Equal<ServletDetail> equal) {

        final ServletDetail populated = new ServletDetailBuilder().withAll().build();
        final ServletDetail empty = new ServletDetailBuilder().withAsyncSupported(true).build();

        assertEquality(populated, equal, new MockDifferent());
        assertEquality(empty, equal, new MockDifferent());
        assertEquality(populated, equal, new NullDifferent());
    }

    private static void assertEquality(ServletDetail left, Equal<ServletDetail> eq, Different different) {

        assertTrue(eq.equal(left, left));
        assertTrue(eq.equal(left, new ServletDetail(left)));

        assertFalse(eq.equal(left, new ServletDetailBuilder(left).withName(different.string()).build()));
        assertFalse(eq.equal(left,
                new ServletDetailBuilder(left).withUrlPatterns(different.list(String.class)).build()));
        assertFalse(eq.equal(left, new ServletDetailBuilder(left).withLoadOnStartup(different.integer()).build()));
        assertFalse(eq.equal(left,
                new ServletDetailBuilder(left).withInitParams(different.map("diff", "erent")).build()));
        assertFalse(eq.equal(left, new ServletDetailBuilder(left).withAsyncSupported(different.bool()).build()));
        assertFalse(eq.equal(left, new ServletDetailBuilder(left).withSmallIcon(different.string()).build()));
        assertFalse(eq.equal(left, new ServletDetailBuilder(left).withLargeIcon(different.string()).build()));
        assertFalse(eq.equal(left, new ServletDetailBuilder(left).withDescription(different.string()).build()));
        assertFalse(eq.equal(left, new ServletDetailBuilder(left).withDisplayName(different.string()).build()));
        assertFalse(eq.equal(left,
                new ServletDetailBuilder(left).withServlet(different.type(DifferentServlet.class)).build()));

        assertFalse(eq.equal(left, new Object()));
        assertFalse(eq.equal(left, null));
    }

    @WebServlet(
            name = NAME,
            value = VALUE,
            urlPatterns = URL_PATTERN,
            loadOnStartup = LOAD_ON_STARTUP,
            initParams = @WebInitParam(name = INIT, value = PARAM),
            asyncSupported = ASYNC_SUPPORT,
            smallIcon = SMALL_ICON,
            largeIcon = LARGE_ICON,
            description = DESCRIPTION,
            displayName = DISPLAY_NAME
    )
    public static class AllAnnotatedServlet extends HttpServlet {
    }

    @WebServlet(VALUE)
    public static class AnnotatedWithValueServlet extends HttpServlet {
    }

    @WebServlet(urlPatterns = URL_PATTERN)
    public static class AnnotatedWithUrlPatternsServlet extends HttpServlet {
    }

    public static class UnannotatedServlet extends HttpServlet {
    }

    public static class PrivateDefaultConstructorServlet extends HttpServlet {
        private PrivateDefaultConstructorServlet() {
        }
    }

    public static class NoDefaultConstructorServlet extends HttpServlet {
        public NoDefaultConstructorServlet(Object arg) {
        }
    }

    public static class DifferentServlet extends HttpServlet {
    }

    private static class ServletDetailBuilder {

        private String name;
        private List<String> urlPatterns;
        private int loadOnStartup;
        private Map<String, String> initParams;
        private boolean asyncSupported;
        private String smallIcon;
        private String largeIcon;
        private String description;
        private String displayName;
        private Class<? extends Servlet> servlet;

        public ServletDetailBuilder() {
        }

        public ServletDetailBuilder(ServletDetail servletDetail) {
            this(
                    servletDetail.getName(),
                    servletDetail.getUrlPatterns(),
                    servletDetail.loadOnStartup(),
                    servletDetail.getInitParams(),
                    servletDetail.asyncSupported(),
                    servletDetail.getSmallIcon(),
                    servletDetail.getLargeIcon(),
                    servletDetail.getDescription(),
                    servletDetail.getDisplayName(),
                    servletDetail.getServlet()
            );
        }

        private ServletDetailBuilder(
                String name,
                List<String> urlPatterns,
                int loadOnStartup,
                Map<String, String> initParams,
                boolean asyncSupported,
                String smallIcon,
                String largeIcon,
                String description,
                String displayName,
                Class<? extends Servlet> servlet
        ) {
            this.name = name;
            this.urlPatterns = urlPatterns;
            this.loadOnStartup = loadOnStartup;
            this.initParams = initParams;
            this.asyncSupported = asyncSupported;
            this.smallIcon = smallIcon;
            this.largeIcon = largeIcon;
            this.description = description;
            this.displayName = displayName;
            this.servlet = servlet;
        }

        public ServletDetail build() {
            return new ServletDetail(
                    name,
                    urlPatterns,
                    loadOnStartup,
                    initParams,
                    asyncSupported,
                    smallIcon,
                    largeIcon,
                    description,
                    displayName,
                    servlet
            );
        }

        public ServletDetailBuilder withAll() {
            withName(NAME);
            withUrlPatterns(asList(URL_PATTERN));
            withLoadOnStartup(LOAD_ON_STARTUP);
            withInitParams(INIT_PARAMS);
            withAsyncSupported(ASYNC_SUPPORT);
            withSmallIcon(SMALL_ICON);
            withLargeIcon(LARGE_ICON);
            withDescription(DESCRIPTION);
            withDisplayName(DISPLAY_NAME);
            withServlet(Servlet.class);
            return this;
        }

        public ServletDetailBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ServletDetailBuilder withUrlPatterns(List<String> urlPatterns) {
            this.urlPatterns = urlPatterns;
            return this;
        }

        public ServletDetailBuilder withLoadOnStartup(int loadOnStartup) {
            this.loadOnStartup = loadOnStartup;
            return this;
        }

        public ServletDetailBuilder withInitParams(Map<String, String> initParams) {
            this.initParams = initParams;
            return this;
        }

        public ServletDetailBuilder withAsyncSupported(boolean asyncSupported) {
            this.asyncSupported = asyncSupported;
            return this;
        }

        public ServletDetailBuilder withSmallIcon(String smallIcon) {
            this.smallIcon = smallIcon;
            return this;
        }

        public ServletDetailBuilder withLargeIcon(String largeIcon) {
            this.largeIcon = largeIcon;
            return this;
        }

        public ServletDetailBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ServletDetailBuilder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public ServletDetailBuilder withServlet(Class<? extends Servlet> servlet) {
            this.servlet = servlet;
            return this;
        }
    }
}
