package shiver.me.timbers.junit.runner;

import org.junit.Test;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AnnotationServletsFactoryTest {

    private static final List<Class<? extends Servlet>> SERVLETS = unmodifiableList(
            new ArrayList<Class<? extends Servlet>>() {{
                add(ServletOne.class);
                add(ServletTwo.class);
                add(ServletThree.class);
            }}
    );

    @Test
    public void No_servlets_are_returned_if_none_are_configured() {

        class TestClass {
        }

        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        assertThat(servlets.getServlets(), empty());
    }

    @Test
    public void Servlets_are_returned_if_some_are_configured() {

        @shiver.me.timbers.junit.runner.annotation.Servlets({ServletOne.class, ServletTwo.class, ServletThree.class})
        class TestClass {
        }

        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        assertEquals(SERVLETS, servlets.getServlets());
    }

    private static abstract class ServletOne implements Servlet {
    }

    private static abstract class ServletThree implements Servlet {
    }

    private static abstract class ServletTwo implements Servlet {
    }
}
