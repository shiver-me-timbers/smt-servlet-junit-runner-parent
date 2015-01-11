package shiver.me.timbers.junit.runner.servlet.test.one;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.PACKAGE_SERVLET_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.PACKAGE_SERVLET_DETAIL_ONE_PATH;

@WebServlet(name = PACKAGE_SERVLET_DETAIL_ONE_NAME, value = PACKAGE_SERVLET_DETAIL_ONE_PATH)
public class PackageServletOne extends HttpServlet {
}
