package shiver.me.timbers.junit.runner.tomcat.test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;

public class Constants {

    public static final String INIT = "init";
    public static final String PARAM = "param";
    public static final String URL_PATTERN = "/test";
    public static final String SUCCESS = "success";
    public static final String FILTERED = "filtered";

    public static final String SERVLET_NAME = "test-servlet";
    public static final String PACKAGE_SERVLET_NAME = "test-package-servlet";
    public static final String WEB_XML_SERVLET_NAME = "test-webxml-servlet";
    public static final String FILTER_NAME = "test-filter";
    public static final String PACKAGE_FILTER_NAME = "test-package-filter";
    public static final String WEB_XML_FILTER_NAME = "test-webxml-filter";

    public static final String PACKAGE_ONE = "shiver.me.timbers.junit.runner.tomcat.test.one";

    public static final String WEB_XML = "web.xml";

    public static Response GET(int port, String path) {
        return ClientBuilder.newClient()
                .target(String.format("http://localhost:%d/%s", port, path))
                .request(TEXT_PLAIN_TYPE).get();
    }
}
