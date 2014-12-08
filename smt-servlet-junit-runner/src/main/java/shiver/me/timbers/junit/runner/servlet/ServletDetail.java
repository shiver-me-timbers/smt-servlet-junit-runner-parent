package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;
import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.buildAnnotation;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.findUrlPatterns;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.transform;

/**
 * This class contains the instantiated servlet and all the details that can be retrieved about it.
 *
 * @author Karl Bennett
 */
public class ServletDetail {

    private final String name;
    private final List<String> urlPatterns;
    private final int loadOnStartup;
    private final Map<String, String> initParams;
    private final boolean asyncSupported;
    private final String smallIcon;
    private final String largeIcon;
    private final String description;
    private final String displayName;

    private final Servlet servlet;

    public ServletDetail(Class<? extends Servlet> servlet) {
        this(instantiate(servlet), buildAnnotation(servlet, WebServlet.class, ClassWebServlet.class));
    }

    private ServletDetail(Servlet servlet, WebServlet webServlet) {
        this(webServlet.name(), findUrlPatterns(webServlet), webServlet.loadOnStartup(),
                transform(webServlet.initParams()), webServlet.asyncSupported(), webServlet.smallIcon(),
                webServlet.largeIcon(), webServlet.description(), webServlet.displayName(), servlet);
    }

    public ServletDetail(String name, List<String> urlPatterns, int loadOnStartup, Map<String, String> initParams,
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

    public int loadOnStartup() {
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
