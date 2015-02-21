package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runners.model.InitializationError;

import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class TomcatJUnitRunnerTest {

    @Test
    public void Tomcat_is_initialised_correctly() throws InitializationError {

        @SuppressWarnings("unchecked")
        final TomcatWrapper<Object, Object, Object, FilterDefWrapper, FilterMapWrapper> tomcat = mock(TomcatWrapper.class);
        final Object jarScanner = new Object();
        final EngineWrapper engine = mock(EngineWrapper.class);
        final Object host = new Object();
        @SuppressWarnings("unchecked")
        final ContextWrapper<Object, FilterDefWrapper, FilterMapWrapper> context = mock(ContextWrapper.class);

        final String name = "engine name";

        // Given
        when(tomcat.getWrappedEngine()).thenReturn(engine);
        when(tomcat.getHost()).thenReturn(host);
        when(tomcat.addWebApp(host, "/", "/")).thenReturn(context);

        when(engine.getName()).thenReturn(name);

        // When
        new TomcatJUnitRunner<>(tomcat, jarScanner, TestClass.class);

        // Then
        verify(tomcat).getWrappedEngine();
        verify(tomcat).getHost();
        verify(tomcat).addWebApp(host, "/", "/");
        verify(engine).getName();
        verify(engine).setName(matches(name + "\\d+"));
        verify(context).setJarScanner(jarScanner);
        verifyNoMoreInteractions(tomcat, engine, context);
    }

    public static class TestClass {

        @Test
        public void test() {
        }
    }
}
