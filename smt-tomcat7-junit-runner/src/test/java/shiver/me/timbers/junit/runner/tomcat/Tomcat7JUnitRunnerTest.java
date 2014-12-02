package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.annotation.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7JUnitRunnerTest.TestServlet;

@RunWith(Tomcat7JUnitRunner.class)
@Servlets(TestServlet.class)
public class Tomcat7JUnitRunnerTest {

    private static final String SUCCESS = "success";

    @Port
    private int port;

    @Test
    public void Tomcat_can_start_up() {

        // Then
        assertThat(port, greaterThan(0));

        final Response response = ClientBuilder.newClient().target(String.format("http://localhost:%d/", port))
                .request(TEXT_PLAIN_TYPE).get();

        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(SUCCESS, response.readEntity(String.class));
    }

    public static class TestServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setStatus(OK.getStatusCode());
            response.setContentType(TEXT_PLAIN);

            final PrintWriter writer = response.getWriter();
            writer.write(SUCCESS);
            writer.flush();
        }
    }
}
