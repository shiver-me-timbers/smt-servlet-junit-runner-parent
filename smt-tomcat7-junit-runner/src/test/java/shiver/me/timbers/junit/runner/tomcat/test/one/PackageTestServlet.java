package shiver.me.timbers.junit.runner.tomcat.test.one;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.OK;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.*;

@WebServlet(
        urlPatterns = URL_PATTERN,
        name = PACKAGE_SERVLET_NAME,
        asyncSupported = true,
        initParams = @WebInitParam(name = INIT, value = PARAM),
        loadOnStartup = 1
)
public class PackageTestServlet extends HttpServlet {

    public static final AtomicReference<Boolean> LOADED = new AtomicReference<>();
    public static final AtomicReference<String> NAME = new AtomicReference<>();
    public static final AtomicReference<String> INIT_PARAM_VALUE = new AtomicReference<>();
    public static final AtomicReference<Boolean> ASYNC_SUPPORTED = new AtomicReference<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        LOADED.set(true);
        NAME.set(config.getServletName());
        INIT_PARAM_VALUE.set(config.getInitParameter(INIT));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ASYNC_SUPPORTED.set(request.isAsyncSupported());

        response.setStatus(OK.getStatusCode());
        response.setContentType(TEXT_PLAIN);

        final PrintWriter writer = response.getWriter();
        writer.write(SUCCESS);
        writer.flush();
    }
}