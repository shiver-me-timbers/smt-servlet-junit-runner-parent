package shiver.me.timbers.junit.runner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ServletJUnitRunnerTest {

    private Runner runner;

    @Before
    public void setUp() {
        runner = mock(Runner.class);
    }

    @Test
    public void The_runner_can_be_run() {

        final RunNotifier notifier = mock(RunNotifier.class);
        final Class<Class> test = Class.class;
        final RunListener runListener = mock(RunListener.class);
        final RunListenerFactory runListenerFactory = mock(RunListenerFactory.class);
        when(runListenerFactory.create(test)).thenReturn(runListener);

        new ServletJUnitRunner(runner, runListenerFactory, test).run(notifier);

        verify(notifier).addListener(runListener);
        verify(runner).run(notifier);
        verifyNoMoreInteractions(runner);
    }

    @Test
    public void The_description_can_be_accessed() {

        final Description expected = mock(Description.class);
        when(runner.getDescription()).thenReturn(expected);

        final Description actual = new ServletJUnitRunner(runner, null, null).getDescription();

        assertEquals(expected, actual);
    }
}
