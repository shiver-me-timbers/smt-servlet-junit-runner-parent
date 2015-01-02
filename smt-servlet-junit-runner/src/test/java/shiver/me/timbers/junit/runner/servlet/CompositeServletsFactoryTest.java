package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.http.HttpServlet;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

/**
 * @author Karl Bennett
 */
public class CompositeServletsFactoryTest {

    private static final Object TEST = new Object();

    @Test
    public void Multiple_servlet_factories_can_be_used_to_create_the_servlets() throws Exception {

        final ServletDetail one = new ServletDetail(One.class);
        final ServletDetail two = new ServletDetail(Two.class);
        final ServletDetail three = new ServletDetail(Three.class);
        final ServletDetail four = new ServletDetail(Four.class);
        final ServletDetail five = new ServletDetail(Five.class);
        final ServletDetail six = new ServletDetail(Six.class);

        final List<ServletDetail> detailsOne = asList(one, two);
        final List<ServletDetail> detailsTwo = asList(three);
        final List<ServletDetail> detailsThree = asList(four, five, six);
        final List<ServletDetail> detailsAll = asList(one, two, three, four, five, six);

        final Servlets servletsOne = mock(Servlets.class);
        final Servlets servletsTwo = mock(Servlets.class);
        final Servlets servletsThree = mock(Servlets.class);
        final Servlets expected = mock(Servlets.class);

        final ServletsFactory factoryOne = mock(ServletsFactory.class);
        final ServletsFactory factoryTwo = mock(ServletsFactory.class);
        final ServletsFactory factoryThree = mock(ServletsFactory.class);

        // Given
        when(servletsOne.asList()).thenReturn(detailsOne);
        when(servletsTwo.asList()).thenReturn(detailsTwo);
        when(servletsThree.asList()).thenReturn(detailsThree);
        when(expected.asList()).thenReturn(detailsAll);
        when(expected.iterator()).thenReturn(detailsAll.iterator());

        when(factoryOne.create(TEST)).thenReturn(servletsOne);
        when(factoryTwo.create(TEST)).thenReturn(servletsTwo);
        when(factoryThree.create(TEST)).thenReturn(servletsThree);

        // When
        final Servlets actual = new CompositeServletsFactory(factoryOne, factoryTwo, factoryThree).create(TEST);

        // Then
        assertThat(actual, equalTo(expected));
    }

    public static class One extends HttpServlet {
    }

    public static class Two extends HttpServlet {
    }

    public static class Three extends HttpServlet {
    }

    public static class Four extends HttpServlet {
    }

    public static class Five extends HttpServlet {
    }

    public static class Six extends HttpServlet {
    }
}
