package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "servlet-one", value = "/one")
public class ServletOne extends HttpServlet {

    public static final String NAME = "servlet-one";
    public static final String URL = "/one";
}
