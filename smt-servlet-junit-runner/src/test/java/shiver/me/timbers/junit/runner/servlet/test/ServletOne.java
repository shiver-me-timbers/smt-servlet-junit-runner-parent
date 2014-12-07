package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "servlet-one", value = "/one")
public class ServletOne extends HttpServlet {
}
