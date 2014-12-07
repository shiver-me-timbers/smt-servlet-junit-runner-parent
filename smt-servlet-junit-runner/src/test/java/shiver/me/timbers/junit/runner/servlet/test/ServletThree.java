package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "servlet-three", urlPatterns = "/three")
public class ServletThree extends HttpServlet {
}
