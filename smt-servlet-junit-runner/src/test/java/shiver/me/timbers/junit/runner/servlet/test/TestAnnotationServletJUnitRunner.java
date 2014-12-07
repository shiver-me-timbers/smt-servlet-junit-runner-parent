package shiver.me.timbers.junit.runner.servlet.test;

import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.AnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.config.PortConfiguration;

public class TestAnnotationServletJUnitRunner extends AnnotationServletJUnitRunner<TestServletContainer> {

    public TestAnnotationServletJUnitRunner(Class test) throws InitializationError {
        super(
                new Container<TestServletContainer>() {

                    private final TestServletContainer container = new TestServletContainer();

                    @Override
                    public void config(PortConfiguration portConfiguration) {
                        container.config(portConfiguration);
                    }

                    @Override
                    public void config(ContainerConfiguration<TestServletContainer> containerConfiguration) {
                        container.config(containerConfiguration);
                        containerConfiguration.configure(container);
                    }

                    @Override
                    public void start() {
                        container.start();
                    }

                    @Override
                    public void load(Servlets servlets) {
                        container.load(servlets);
                    }

                    @Override
                    public void load(Filters filters) {
                        container.load(filters);
                    }

                    @Override
                    public void shutdown() {
                        container.shutdown();
                    }
                },
                test
        );
    }
}
