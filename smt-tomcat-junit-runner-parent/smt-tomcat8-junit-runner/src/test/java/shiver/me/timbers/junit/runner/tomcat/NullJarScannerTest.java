package shiver.me.timbers.junit.runner.tomcat;

import org.apache.tomcat.JarScanFilter;
import org.apache.tomcat.JarScanType;
import org.apache.tomcat.JarScannerCallback;
import org.junit.Test;

import javax.servlet.ServletContext;

import static org.apache.tomcat.JarScanType.TLD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class NullJarScannerTest {

    @Test
    public void The_null_jar_scanner_does_nothing() throws Exception {

        final JarScanType scanType = TLD;
        final ServletContext context = mock(ServletContext.class);
        final JarScannerCallback callback = mock(JarScannerCallback.class);
        final String jarName = "jar name";
        final JarScanFilter expected = mock(JarScanFilter.class);

        final NullJarScanner scanner = new NullJarScanner();

        // Given
        when(expected.check(scanType, jarName)).thenReturn(true);

        // When
        scanner.setJarScanFilter(expected);
        final JarScanFilter actual = scanner.getJarScanFilter();
        scanner.scan(scanType, context, callback);

        // Then
        assertFalse(actual.check(scanType, jarName));
        assertNotEquals(expected, actual);
        verifyZeroInteractions(expected, context, callback);
    }
}
