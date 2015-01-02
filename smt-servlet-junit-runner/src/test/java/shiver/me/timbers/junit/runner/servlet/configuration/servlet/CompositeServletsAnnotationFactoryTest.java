package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

import javax.servlet.http.HttpServlet;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

/**
 * @author Karl Bennett
 */
public class CompositeServletsAnnotationFactoryTest {

    @Test
    public void No_servlet_factories_can_be_used_to_create_no_servlets() throws Exception {

        // Given
        final Servlets expected = mockEmptyServlets();

        // When
        final Servlets actual = new CompositeServletsAnnotationFactory().create(mock(ContainerConfiguration.class));

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void Multiple_servlet_factories_can_be_used_to_create_no_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Servlets> factoryOne = mock(AnnotationFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Servlets> factoryTwo = mock(AnnotationFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Servlets> factoryThree = mock(AnnotationFactory.class);

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);

        final Servlets expected = mockEmptyServlets();

        // Given
        when(factoryOne.create(configuration)).thenReturn(expected);
        when(factoryTwo.create(configuration)).thenReturn(expected);
        when(factoryThree.create(configuration)).thenReturn(expected);

        // When
        final Servlets actual = new CompositeServletsAnnotationFactory(factoryOne, factoryTwo, factoryThree)
                .create(configuration);

        // Then
        assertThat(actual, equalTo(expected));
    }

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

        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Servlets> factoryOne = mock(AnnotationFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Servlets> factoryTwo = mock(AnnotationFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Servlets> factoryThree = mock(AnnotationFactory.class);

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);

        // Given
        when(servletsOne.asList()).thenReturn(detailsOne);
        when(servletsTwo.asList()).thenReturn(detailsTwo);
        when(servletsThree.asList()).thenReturn(detailsThree);
        when(expected.asList()).thenReturn(detailsAll);
        when(expected.iterator()).thenReturn(detailsAll.iterator());

        when(factoryOne.create(configuration)).thenReturn(servletsOne);
        when(factoryTwo.create(configuration)).thenReturn(servletsTwo);
        when(factoryThree.create(configuration)).thenReturn(servletsThree);

        // When
        final Servlets actual = new CompositeServletsAnnotationFactory(factoryOne, factoryTwo, factoryThree)
                .create(configuration);

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
