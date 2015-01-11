package shiver.me.timbers.junit.runner.servlet;

import org.junit.rules.MethodRule;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.WebXmlFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.filter.FiltersFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.servlet.ServletsFactory;
import shiver.me.timbers.junit.runner.servlet.inject.PortSetter;

import java.net.URL;
import java.util.List;

/**
 * This runner should be used to compose a working servlet runner.
 *
 * @author Karl Bennett
 */
public class ServletJUnitRunner<C> extends BlockJUnit4ClassRunner {

    private final PortConfigurationFactory portConfigurationFactory;
    private final FiltersFactory filtersFactory;
    private final WebXmlFactory webXmlFactory;
    private final ContainerConfigurationFactory<C> containerConfigurationFactory;
    private final ServletsFactory servletsFactory;
    private final PortSetter portSetter;
    private final RunListenerFactory runListenerFactory;
    private final Container<C> container;

    public ServletJUnitRunner(
            PortConfigurationFactory portConfigurationFactory,
            ServletsFactory servletsFactory,
            FiltersFactory filtersFactory,
            WebXmlFactory webXmlFactory,
            ContainerConfigurationFactory<C> containerConfigurationFactory,
            PortSetter portSetter,
            RunListenerFactory runListenerFactory,
            Container<C> container,
            Class test
    ) throws InitializationError {
        super(test);
        this.portConfigurationFactory = portConfigurationFactory;
        this.filtersFactory = filtersFactory;
        this.webXmlFactory = webXmlFactory;
        this.containerConfigurationFactory = containerConfigurationFactory;
        this.servletsFactory = servletsFactory;
        this.portSetter = portSetter;
        this.runListenerFactory = runListenerFactory;
        this.container = container;
    }

    @Override
    protected List<MethodRule> rules(Object target) {

        final PortConfiguration portConfiguration = portConfigurationFactory.create(target);

        final ContainerConfiguration<C> containerConfiguration = containerConfigurationFactory.create(target);

        final Servlets servlets = servletsFactory.create(target);

        final Filters filters = filtersFactory.create(target);

        final URL webXml = webXmlFactory.create(target);

        container.configure(portConfiguration);
        container.configure(containerConfiguration);
        container.load(servlets);
        container.load(filters);
        load(container, webXml);
        container.start();

        portSetter.set(target, portConfiguration);

        return super.rules(target);
    }

    private static void load(Container container, URL webXml) {
        if (null != webXml) {
            container.load(webXml);
        }
    }

    @Override
    public void run(RunNotifier notifier) {

        notifier.addListener(runListenerFactory.create(container));

        super.run(notifier);
    }
}
