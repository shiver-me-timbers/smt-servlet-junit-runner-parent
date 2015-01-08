package shiver.me.timbers.junit.runner.servlet;

import java.util.List;
import java.util.Map;

public class NullDifferent implements Different {

    @Override
    public <T> List<T> list(Class<T> type) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> map(K key, V value) {
        return null;
    }

    @Override
    public String string() {
        return null;
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
        return null;
    }
}
