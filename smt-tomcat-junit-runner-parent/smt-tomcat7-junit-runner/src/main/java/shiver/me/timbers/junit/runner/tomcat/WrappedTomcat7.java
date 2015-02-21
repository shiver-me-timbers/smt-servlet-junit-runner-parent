package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.JarScanner;

import javax.servlet.Servlet;

/**
 * @author Karl Bennett
 */
public class WrappedTomcat7 extends Tomcat
        implements TomcatWrapper<Tomcat, Host, JarScanner, WrappedFilterDef, WrappedFilterMap> {

    private String contextPath;

    @Override
    public EngineWrapper getWrappedEngine() {

        final Engine engine = getEngine();

        return new WrappedEngine(engine);
    }

    @Override
    public ContextWrapper<JarScanner, WrappedFilterDef, WrappedFilterMap> addWebApp(Host host, String url, String path) {

        final Context context = addWebapp(host, url, path);

        contextPath = context.getPath();

        return new WrappedContext(context);
    }

    @Override
    public Tomcat getDelegate() {
        return this;
    }

    @Override
    public ServletWrapper addServlet(String name, Servlet servlet) {

        final Wrapper wrapper = addServlet(contextPath, name, servlet);

        return new WrappedServlet(wrapper);
    }

}
