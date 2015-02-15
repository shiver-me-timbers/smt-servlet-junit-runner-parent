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

package shiver.me.timbers.junit.runner.servlet.test;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import shiver.me.timbers.junit.runner.servlet.ListIterable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Constants {

    public static URL url() {
        return url("file:/url");
    }

    public static URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final int PORT = 9996;

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

    public static final String WEB_XML_PATH = "web.xml";
    public static final URL WEB_XML_URL = Thread.currentThread().getContextClassLoader().getResource(WEB_XML_PATH);

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
