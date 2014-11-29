package shiver.me.timbers.junit.runner;

import org.junit.rules.MethodRule;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

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
 * The server can be configured for an individual test class with a method that returns a {@link ContainerConfig} that
 * has been annotated with {@link Config}. This will cause a new server instance to start up for that specific test
 * class.
 *
 * If the same configuration can be used across multiple test classes then the classes can be annotated with
 * {@code Config} that has it's value set to an implementation of {@link ContainerConfig}.
 *
 * @author Karl Bennett
 */
public class ServletJUnitRunner<C> extends BlockJUnit4ClassRunner {

    private final Container<C> container;
    private final PortFactory portFactory;
    private final ServletsFactory servletsFactory;
    private final ContainerConfigFactory<C> containerConfigFactory;
    private final RunListenerFactory runListenerFactory;

    public ServletJUnitRunner(
            Container<C> container,
            PortFactory portFactory,
            ServletsFactory servletsFactory,
            ContainerConfigFactory<C> containerConfigFactory,
            RunListenerFactory runListenerFactory,
            Class test
    ) throws InitializationError {
        super(test);
        this.container = container;
        this.portFactory = portFactory;
        this.servletsFactory = servletsFactory;
        this.containerConfigFactory = containerConfigFactory;
        this.runListenerFactory = runListenerFactory;
    }

    @Override
    protected List<MethodRule> rules(Object target) {

        final PortConfig portConfig = portFactory.create(target);

        final ContainerConfig<C> containerConfig = containerConfigFactory.create(target);

        final Servlets servlets = servletsFactory.create(target);

        container.config(portConfig);
        container.config(containerConfig);
        container.load(servlets);
        container.start();

        return super.rules(target);
    }

    @Override
    public void run(RunNotifier notifier) {

        notifier.addListener(runListenerFactory.create(container));

        super.run(notifier);
    }
}
