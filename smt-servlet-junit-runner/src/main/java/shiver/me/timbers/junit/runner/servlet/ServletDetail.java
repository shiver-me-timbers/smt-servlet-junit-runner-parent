package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
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
    private final Class<? extends Servlet> servlet;

    public ServletDetail(Class<? extends Servlet> servlet) {
        this(servlet, buildAnnotation(servlet, WebServlet.class, ClassWebServlet.class));
    }

    public ServletDetail(Class<? extends Servlet> servlet, WebServlet webServlet) {
        this(
                webServlet.name(),
                findUrlPatterns(webServlet),
                webServlet.loadOnStartup(),
                transform(webServlet.initParams()),
                webServlet.asyncSupported(),
                webServlet.smallIcon(),
                webServlet.largeIcon(),
                webServlet.description(),
                webServlet.displayName(),
                servlet
        );
    }

    public ServletDetail(ServletDetail servletDetail) {
        this(
                servletDetail.getName(),
                servletDetail.getUrlPatterns(),
                servletDetail.loadOnStartup(),
                servletDetail.getInitParams(),
                servletDetail.asyncSupported(),
                servletDetail.getSmallIcon(),
                servletDetail.getLargeIcon(),
                servletDetail.getDescription(),
                servletDetail.getDisplayName(),
                servletDetail.getServlet()
        );
    }

    public ServletDetail(
            String name,
            List<String> urlPatterns,
            int loadOnStartup,
            Map<String, String> initParams,
            boolean asyncSupported,
            String smallIcon,
            String largeIcon,
            String description,
            String displayName,
            Class<? extends Servlet> servlet
    ) {
        this.name = name;
        this.urlPatterns = null == urlPatterns ? null : unmodifiableList(urlPatterns);
        this.loadOnStartup = loadOnStartup;
        this.initParams = initParams;
        this.asyncSupported = asyncSupported;
        this.smallIcon = smallIcon;
        this.largeIcon = largeIcon;
        this.description = description;
        this.displayName = displayName;
        this.servlet = servlet;
    }

    public Class<? extends Servlet> getServlet() {
        return servlet;
    }

    public Servlet getServletInstance() {
        return instantiate(servlet);
    }

    public String getName() {
        return name;
    }

    public List<String> getUrlPatterns() {
        return null == urlPatterns ? null : new ArrayList<>(urlPatterns);
    }

    public int loadOnStartup() {
        return loadOnStartup;
    }

    public Map<String, String> getInitParams() {
        return null == initParams ? null : new HashMap<>(initParams);
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

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ServletDetail that = (ServletDetail) o;

        if (asyncSupported != that.asyncSupported) {
            return false;
        }
        if (loadOnStartup != that.loadOnStartup) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) {
            return false;
        }
        if (initParams != null ? !initParams.equals(that.initParams) : that.initParams != null) {
            return false;
        }
        if (largeIcon != null ? !largeIcon.equals(that.largeIcon) : that.largeIcon != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (servlet != null ? !servlet.equals(that.servlet) : that.servlet != null) {
            return false;
        }
        if (smallIcon != null ? !smallIcon.equals(that.smallIcon) : that.smallIcon != null) {
            return false;
        }
        if (urlPatterns != null ? !urlPatterns.equals(that.urlPatterns) : that.urlPatterns != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (urlPatterns != null ? urlPatterns.hashCode() : 0);
        result = 31 * result + loadOnStartup;
        result = 31 * result + (initParams != null ? initParams.hashCode() : 0);
        result = 31 * result + (asyncSupported ? 1 : 0);
        result = 31 * result + (smallIcon != null ? smallIcon.hashCode() : 0);
        result = 31 * result + (largeIcon != null ? largeIcon.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (servlet != null ? servlet.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {

        return format(
                "ServletDetail {\n" +
                        "name='%s',\n" +
                        "urlPatterns=%s,\n" +
                        "loadOnStartup=%d,\n" +
                        "initParams=%s,\n" +
                        "asyncSupported=%s,\n" +
                        "smallIcon='%s',\n" +
                        "largeIcon='%s',\n" +
                        "description='%s',\n" +
                        "displayName='%s',\n" +
                        "servlet=%s\n" +
                        "}",
                name,
                urlPatterns,
                loadOnStartup,
                initParams,
                asyncSupported,
                smallIcon,
                largeIcon,
                description,
                displayName,
                servlet
        );
    }
}
