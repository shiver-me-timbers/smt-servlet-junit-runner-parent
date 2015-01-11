package shiver.me.timbers.junit.runner.servlet.test.three;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_SERVLET_DETAIL_TWO_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_SERVLET_DETAIL_TWO_PATH;

@WebServlet(name = PACKAGE_SERVLET_DETAIL_TWO_NAME, value = PACKAGE_SERVLET_DETAIL_TWO_PATH)
public class PackageServletTwo extends HttpServlet {
}
