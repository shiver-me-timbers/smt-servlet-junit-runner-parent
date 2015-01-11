package shiver.me.timbers.junit.runner.servlet.test;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import shiver.me.timbers.junit.runner.servlet.ListIterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Constants {

    public static final String INIT = "init";
    public static final String PARAM = "param";

    public static final String DESCRIPTION = "description";
    public static final String DISPLAY_NAME = "displayName";
    public static final Map<String, String> INIT_PARAMS = singletonMap(INIT, PARAM);
    public static final String SMALL_ICON = "smallIcon";
    public static final String LARGE_ICON = "largeIcon";
    public static final String VALUE = "/value";
    public static final String URL_PATTERN = "/url-pattern";
    public static final boolean ASYNC_SUPPORT = true;

    @SafeVarargs
    public static <E, T extends ListIterable<T, E>> T mockListIterable(Class<T> type, E... elements) {

        final List<E> list = new ArrayList<>();

        Collections.addAll(list, elements);

        return mockListIterable(type, list);
    }

    public static <E, T extends ListIterable<T, E>> T mockListIterable(Class<T> type, List<E> list) {

        final T mock = mock(type);
        when(mock.asList()).thenReturn(list);
        when(mock.iterator()).thenAnswer(new IteratorAnswer<>(list));

        return mock;
    }

    private static class IteratorAnswer<T> implements Answer<Iterator<T>> {

        private final Iterable<T> list;

        private IteratorAnswer(Iterable<T> list) {
            this.list = list;
        }

        @Override
        public Iterator<T> answer(InvocationOnMock invocationOnMock) throws Throwable {
            return list.iterator();
        }
    }
}
