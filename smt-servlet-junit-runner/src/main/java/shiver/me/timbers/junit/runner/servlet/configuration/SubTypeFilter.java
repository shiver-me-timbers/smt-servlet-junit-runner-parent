package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SubTypeFilter<T> implements Factory<List<Class>, List<Class<? extends T>>> {

    private final Class<T> type;

    public SubTypeFilter(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Class<? extends T>> create(List<Class> classes) {

        final List<Class<? extends T>> subTypes = new ArrayList<>();

        for (Class type : classes) {
            if (this.type.isAssignableFrom(type)) {
                subTypes.add(type);
            }
        }

        return subTypes;
    }
}
