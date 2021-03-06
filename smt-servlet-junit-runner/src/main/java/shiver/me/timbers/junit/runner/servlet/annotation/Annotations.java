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

package shiver.me.timbers.junit.runner.servlet.annotation;

import javax.servlet.annotation.WebInitParam;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static shiver.me.timbers.junit.runner.servlet.Reflections.callMethod;
import static shiver.me.timbers.junit.runner.servlet.Reflections.getMethod;
import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;

/**
 * Simple reflection utilities.
 *
 * @author Karl Bennett
 */
public class Annotations {

    /**
     * @param type           the class to get the annotation from.
     * @param annotationType the annotation to get.
     * @param wrapper        a wrapper class that will be instantiated if the annotation cannot be found. It will be
     *                       instantiated with the {@code type} passed as the only argument to it's constructor.
     * @return the required annotation.
     */
    public static <A extends Annotation> A buildAnnotation(Class<?> type, Class<A> annotationType,
                                                           Class<? extends A> wrapper) {

        final A annotation = type.getAnnotation(annotationType);

        if (null == annotation) {
            return instantiate(wrapper, type);
        }

        return annotation;
    }

    public static Map<String, String> transform(WebInitParam... webInitParams) {

        final Map<String, String> initParams = new HashMap<>();

        for (WebInitParam webInitParam : webInitParams) {
            initParams.put(webInitParam.name(), webInitParam.value());
        }

        return initParams;
    }

    public static List<String> findUrlPatterns(Annotation annotation) {

        final String[] value = callMethod(annotation, getMethod(annotation, "value"));

        if (0 < value.length) {
            return asList(value);
        }

        return asList((String[]) callMethod(annotation, getMethod(annotation, "urlPatterns")));
    }
}
