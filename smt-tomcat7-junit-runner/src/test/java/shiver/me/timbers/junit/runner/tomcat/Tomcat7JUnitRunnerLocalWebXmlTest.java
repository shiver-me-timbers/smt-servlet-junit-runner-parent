package shiver.me.timbers.junit.runner.tomcat;

import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

@RunWith(Tomcat7JUnitRunner.class)
@ContainerConfiguration(webXml = "local/web.xml")
public class Tomcat7JUnitRunnerLocalWebXmlTest extends ServletJUnitRunnerWebXmlTest {
}
