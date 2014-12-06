package shiver.me.timbers.junit.runner.servlet;

import org.junit.rules.MethodRule;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfigFactory;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;
import shiver.me.timbers.junit.runner.servlet.config.PortConfigFactory;

import java.util.List;

/**
 * This runner should be used to compose a working servlet runner.
 *
 * @author Karl Bennett
 */
public class ServletJUnitRunner<C> extends BlockJUnit4ClassRunner {

    private final PortConfigFactory portConfigFactory;
    private final ContainerConfigFactory<C> containerConfigFactory;
    private final ServletsFactory servletsFactory;
    private final PortSetter portSetter;
    private final RunListenerFactory runListenerFactory;
    private final Container<C> container;

    public ServletJUnitRunner(
            PortConfigFactory portConfigFactory,
            ServletsFactory servletsFactory,
            ContainerConfigFactory<C> containerConfigFactory,
            PortSetter portSetter,
            RunListenerFactory runListenerFactory,
            Container<C> container,
            Class test
    ) throws InitializationError {
        super(test);
        this.portConfigFactory = portConfigFactory;
        this.containerConfigFactory = containerConfigFactory;
        this.servletsFactory = servletsFactory;
        this.portSetter = portSetter;
        this.runListenerFactory = runListenerFactory;
        this.container = container;
    }

    @Override
    protected List<MethodRule> rules(Object target) {

        final PortConfig portConfig = portConfigFactory.create(target);

        final ContainerConfiguration<C> containerConfiguration = containerConfigFactory.create(target);

        final Servlets servlets = servletsFactory.create(target);

        container.config(portConfig);
        container.config(containerConfiguration);
        container.load(servlets);
        container.start();

        portSetter.set(target, portConfig);

        return super.rules(target);
    }

    @Override
    public void run(RunNotifier notifier) {

        notifier.addListener(runListenerFactory.create(container));

        super.run(notifier);
    }
}
