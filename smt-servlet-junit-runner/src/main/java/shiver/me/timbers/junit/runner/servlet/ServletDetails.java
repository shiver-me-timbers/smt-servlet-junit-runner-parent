package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * This class contains the instantiated servlet and all the details that can be retrieved about it.
 *
 * @author Karl Bennett
 */
public class ServletDetails {

    private static Servlet instantiate(Class<? extends Servlet> servlet) {
        try {
            return servlet.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static WebServlet webServlet(Class<? extends Servlet> servlet) {

        final WebServlet webServlet = servlet.getAnnotation(WebServlet.class);

        if (null == webServlet) {
            return new ClassWebServlet(servlet);
        }

        return webServlet;
    }

    private static List<String> findUrlPatterns(WebServlet webServlet) {

        final String[] value = webServlet.value();

        if (0 < value.length) {
            return asList(value);
        }

        return asList(webServlet.urlPatterns());
    }

    private static Map<String, String> transform(WebInitParam[] webInitParams) {

        final Map<String, String> initParams = new HashMap<>();

        for (WebInitParam webInitParam : webInitParams) {
            initParams.put(webInitParam.name(), webInitParam.value());
        }

        return initParams;
    }

    private final String name;
    private final List<String> urlPatterns;
    private final boolean loadOnStartup;
    private final Map<String, String> initParams;
    private final boolean asyncSupported;
    private final String smallIcon;
    private final String largeIcon;
    private final String description;
    private final String displayName;

    private final Servlet servlet;

    public ServletDetails(Class<? extends Servlet> servlet) {
        this(instantiate(servlet), webServlet(servlet));
    }

    private ServletDetails(Servlet servlet, WebServlet webServlet) {
        this(webServlet.name(), findUrlPatterns(webServlet), 0 <= webServlet.loadOnStartup(),
                transform(webServlet.initParams()), webServlet.asyncSupported(), webServlet.smallIcon(),
                webServlet.largeIcon(), webServlet.description(), webServlet.displayName(), servlet);
    }

    public ServletDetails(String name, List<String> urlPatterns, boolean loadOnStartup, Map<String, String> initParams,
                          boolean asyncSupported, String smallIcon, String largeIcon, String description,
                          String displayName, Servlet servlet) {
        this.name = name;
        this.urlPatterns = unmodifiableList(urlPatterns);
        this.loadOnStartup = loadOnStartup;
        this.initParams = initParams;
        this.asyncSupported = asyncSupported;
        this.smallIcon = smallIcon;
        this.largeIcon = largeIcon;
        this.description = description;
        this.displayName = displayName;
        this.servlet = servlet;
    }

    public Servlet getServlet() {
        return servlet;
    }

    public String getName() {
        return name;
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public boolean loadOnStartup() {
        return loadOnStartup;
    }

    public Map<String, String> getInitParams() {
        return initParams;
    }

    public boolean asyncSupported() {
        return asyncSupported;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public String getLargeIcon() {
        return largeIcon;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return displayName;
    }
}
