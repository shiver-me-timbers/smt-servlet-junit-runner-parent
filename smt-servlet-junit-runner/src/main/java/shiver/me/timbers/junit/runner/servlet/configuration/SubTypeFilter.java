package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SubTypeFilter<T> implements Factory<List<Class>, List<Class<? extends T>>> {

    private final Logger log = LoggerFactory.getLogger(SubTypeFilter.class);

    private final Class<T> type;

    public SubTypeFilter(Class<T> type) {
        this.type = type;

        log.debug("Constructed.");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Class<? extends T>> create(List<Class> classes) {

        final List<Class<? extends T>> subTypes = new ArrayList<>();

        log.debug("Filtering out subtypes of {}", type);
        for (Class type : classes) {
            if (this.type.isAssignableFrom(type)) {
                log.debug("Subtype {} found for {}", type, this.type);
                subTypes.add(type);
            }
        }
        log.debug("Filter has left {}", subTypes);
        return subTypes;
    }
}
