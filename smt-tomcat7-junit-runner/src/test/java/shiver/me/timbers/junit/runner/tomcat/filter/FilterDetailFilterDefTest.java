package shiver.me.timbers.junit.runner.tomcat.filter;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;

import javax.servlet.Filter;
import java.lang.reflect.Field;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterDetailFilterDefTest {

    @Test
    public void A_filter_def_can_be_created_with_a_filter_detail() {

        final Filter filter = mock(Filter.class);

        final FilterDetail expected = mock(FilterDetail.class);

        // Given
        when(expected.getFilterInstance()).thenReturn(filter);
        when(expected.getDescription()).thenReturn("description");
        when(expected.getDisplayName()).thenReturn("displayName");
        when(expected.getInitParams()).thenReturn(singletonMap("init", "param"));
        when(expected.getFilterName()).thenReturn("filterName");
        when(expected.getSmallIcon()).thenReturn("smallIcon");
        when(expected.getLargeIcon()).thenReturn("largeIcon");
        when(expected.asyncSupported()).thenReturn(true);

        // When
        final FilterDetailFilterDef actual = new FilterDetailFilterDef(expected);

        // Then
        assertEquals(expected.getFilterInstance(), actual.getFilter());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getDisplayName(), actual.getDisplayName());
        assertEquals(expected.getInitParams(), getFieldValue(actual.getClass().getSuperclass(), actual, "parameters"));
        assertEquals(expected.getFilterName(), actual.getFilterName());
        assertEquals(expected.getSmallIcon(), actual.getSmallIcon());
        assertEquals(expected.getLargeIcon(), actual.getLargeIcon());
        assertEquals(String.valueOf(expected.asyncSupported()), actual.getAsyncSupported());
    }

    @SuppressWarnings("unchecked")
    private static <T> T getFieldValue(Class type, Object object, String name) {

        try {
            final Field field = type.getDeclaredField(name);
            field.setAccessible(true);

            return (T) field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
