package shiver.me.timbers.junit.runner;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

/**
 * Adding this runner to you JUnit class will cause a servlet server to start up before the test.
 *
 * The server will start up on a random free port which can be accessed within the test by annotating an
 * {@code Integer}/{@code int} field with {@link Port}. The port number can be set manually by instead annotating the
 * test class with with {@code Port} and setting it's value to the desired port number.
 *
 * By default the started server will scan the package of the test file for any classes annotated with
 * {@link javax.servlet.annotation.WebServlet} which it will then load.
 *
 * Alternatively the {@link Servlets} annotation can be used to restrict which servlet classes are loaded.
 *
 * The server can be configured for an individual test class by annotating a method that has a single argument of
 * {@link Container} with {@link Config}. This will cause a new server instance to start up for that specific test
 * class.
 *
 * If the same configuration can be used across multiple test classes then the classes can be annotated with
 * {@code Config} with it's value set to the class of the same implementation of the {@link ServerConfig} interface.
 *
 * @author Karl Bennett
 */
public class ServletJUnitRunner extends Runner {

    private final Runner runner;
    private final RunListenerFactory runListenerFactory;
    private final Class<Class> test;

    public ServletJUnitRunner(Runner runner, RunListenerFactory runListenerFactory, Class<Class> test) {
        this.runner = runner;
        this.runListenerFactory = runListenerFactory;
        this.test = test;
    }

    @Override
    public Description getDescription() {
        return runner.getDescription();
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addListener(runListenerFactory.create(test));

        runner.run(notifier);
    }
}
