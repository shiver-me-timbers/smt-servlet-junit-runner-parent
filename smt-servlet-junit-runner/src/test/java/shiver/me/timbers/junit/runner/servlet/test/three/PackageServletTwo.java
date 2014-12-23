package shiver.me.timbers.junit.runner.servlet.test.three;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "package-servlet-two", value = "/package-two")
public class PackageServletTwo extends HttpServlet {
}
