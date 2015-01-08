package shiver.me.timbers.junit.runner.servlet;

import java.util.List;
import java.util.Map;

public interface Different {

    <T> List<T> list(Class<T> type);

    <K, V> Map<K, V> map(K key, V value);

    String string();

    int integer();

    boolean bool();

    <T> Class<T> type(Class<T> type);
}
