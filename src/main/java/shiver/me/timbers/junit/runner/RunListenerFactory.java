package shiver.me.timbers.junit.runner;

import org.junit.runner.notification.RunListener;

public interface RunListenerFactory {

    RunListener create(Class<Class> test);
}
