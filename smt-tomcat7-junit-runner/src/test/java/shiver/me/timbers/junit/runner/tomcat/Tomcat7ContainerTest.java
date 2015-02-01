package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7Container.copy;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7Container.copyAndClose;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7Container.tempWebXml;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7Container.withException;

public class Tomcat7ContainerTest {

    public static final URL WEB_XML_JAR = Thread.currentThread().getContextClassLoader().getResource("web.xml");

    @Test
    public void It_is_possible_to_run_with_a_life_cycle() {

        // Given
        final Callable<Boolean> lifeCycle = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                return true;
            }
        };

        // When
        final Boolean actual = withException(lifeCycle);

        // Then
        assertTrue(actual);
    }

    @Test(expected = RuntimeException.class)
    public void It_is_possible_to_run_with_a_life_cycle_and_have_it_fail() {

        // Given
        final Callable<Void> lifeCycle = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                throw new Exception();
            }
        };

        // When
        withException(lifeCycle);
    }

    @Test
    public void It_is_possible_to_create_a_temporary_web_xml() throws IOException {

        // Given
        final int hash = new Random().nextInt();

        // When
        final File webXml = tempWebXml(hash);

        // Then
        assertTrue(webXml.createNewFile());
    }

    @Test
    public void It_is_possible_to_copy_and_close_a_jar_web_xml_to_the_filesystem() throws IOException {

        // Given
        final int hash = new Random().nextInt();
        final File webXml = tempWebXml(hash);
        final InputStream input = WEB_XML_JAR.openStream();
        final OutputStream output = new FileOutputStream(webXml);

        // When
        copyAndClose(input, output);

        // Then
        assertTrue(webXml.exists());
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_and_close_from_an_invalid_input() throws IOException {

        final InputStream input = mock(InputStream.class);
        final OutputStream output = mock(OutputStream.class);

        // Given
        when(input.read(any(byte[].class))).thenThrow(new IOException());

        // When
        copyAndClose(input, output);
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_and_close_from_an_invalid_output() throws IOException {

        final InputStream input = WEB_XML_JAR.openStream();
        final OutputStream output = mock(OutputStream.class);

        // Given
        doThrow(new IOException()).when(output).write(any(byte[].class), any(Integer.class), any(Integer.class));

        // When
        copyAndClose(input, output);
    }

    @Test
    public void It_is_possible_to_copy_a_jar_web_xml_to_the_filesystem() {

        // Given
        final int hash = new Random().nextInt();
        final File output = tempWebXml(hash);

        // When
        final String webXml = copy(WEB_XML_JAR, output);

        // Then
        assertTrue(new File(webXml).exists());
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_from_an_invalid_web_xml() throws MalformedURLException {

        // Given
        final int hash = new Random().nextInt();
        final URL input = new URL("file:/invalid.jar!web.xml");
        final File output = tempWebXml(hash);

        // When
        copy(input, output);
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_to_an_invalid_web_xml() {

        // Given
        final File output = new File("invalid/web.xml");

        // When
        copy(WEB_XML_JAR, output);
    }
}
