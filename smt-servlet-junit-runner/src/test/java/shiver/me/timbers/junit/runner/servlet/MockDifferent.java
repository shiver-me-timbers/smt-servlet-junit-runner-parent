package shiver.me.timbers.junit.runner.servlet;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonMap;

public class MockDifferent implements Different {

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> list(Class<T> type) {
        return emptyList();
    }

    @Override
    public <K, V> Map<K, V> map(K key, V value) {
        return singletonMap(key, value);
    }

    @Override
    public String string() {
        return "different string";
    }

    @Override
    public int integer() {
        return -1;
    }

    @Override
    public boolean bool() {
        return false;
    }

    @Override
    public <T> Class<T> type(Class<T> type) {
        return type;
    }
}
