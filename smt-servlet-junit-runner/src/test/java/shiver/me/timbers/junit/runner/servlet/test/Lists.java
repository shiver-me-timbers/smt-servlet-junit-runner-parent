package shiver.me.timbers.junit.runner.servlet.test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class Lists {

    @SafeVarargs
    public static <T> List<T> toList(T... elements) {

        final List<T> aggregate = new ArrayList<>();

        addAll(aggregate, elements);

        return aggregate;
    }

    @SafeVarargs
    public static <T> List<T> toList(List<T> list, T... elements) {

        final List<T> aggregate = new ArrayList<>();

        addAll(aggregate, elements);

        return toList(list, aggregate);
    }

    @SafeVarargs
    public static <T> List<T> toList(List<T>... lists) {

        final List<T> list = new ArrayList<>();

        for (List<T> part : lists) {
            list.addAll(part);
        }

        return list;
    }
}
