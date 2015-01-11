package shiver.me.timbers.junit.runner.servlet.annotation;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;

/**
 * This annotation is used to configure a {@link Filter} class that hasn't already been configured. It can also be used
 * to override the configuration of a {@code Filter} that has already been configured.
 *
 * @author Karl Bennett
 */
public @interface FilterConfiguration {

    /**
     * The configuration for the {@code Filter} that should be loaded for this test.
     */
    WebFilter configuration();

    /**
     * The {@code Filter} that should be loaded for this test.
     */
    Class<? extends Filter> servlet();
}
