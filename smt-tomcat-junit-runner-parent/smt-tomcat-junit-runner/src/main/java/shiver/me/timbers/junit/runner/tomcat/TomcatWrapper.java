package shiver.me.timbers.junit.runner.tomcat;

import javax.servlet.Servlet;

/**
 * @author Karl Bennett
 */
public interface TomcatWrapper<D, H, JS, FD extends FilterDefWrapper, FM extends FilterMapWrapper> {

    void setPort(int port);

    EngineWrapper getWrappedEngine();

    H getHost();

    ContextWrapper<JS, FD, FM> addWebApp(H host, String url, String path);

    D getDelegate();

    ServletWrapper addServlet(String name, Servlet servlet);

    void start() throws Exception;

    void stop() throws Exception;
}
