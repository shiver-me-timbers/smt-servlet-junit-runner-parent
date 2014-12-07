package shiver.me.timbers.junit.runner.tomcat.filter;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class FilterDetailFilterMapTest {

    @Test
    public void A_filter_map_can_be_created_with_a_filter_detail() {
        // Given
        final FilterDetail expected = mock(FilterDetail.class);

        // When
        final FilterDetailFilterMap actual = new FilterDetailFilterMap(expected);

        // Then
        assertEquals(expected.getFilterName(), actual.getFilterName());
        assertEquals(expected.getServletNames(), asList(actual.getServletNames()));
        assertEquals(expected.getUrlPatterns(), asList(actual.getURLPatterns()));
        assertEquals(transform(expected.getDispatcherTypes()), asList(actual.getDispatcherNames()));
    }

    private static List<String> transform(List<DispatcherType> dispatcherTypes) {

        final List<String> dispatcherNames = new ArrayList<>();

        for (DispatcherType dispatcherType : dispatcherTypes) {
            dispatcherNames.add(dispatcherType.name());
        }

        return dispatcherNames;
    }
}
