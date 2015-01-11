package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.SERVLET_DETAIL_THREE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.SERVLET_DETAIL_THREE_PATH;

@WebServlet(name = SERVLET_DETAIL_THREE_NAME, urlPatterns = SERVLET_DETAIL_THREE_PATH)
public class ServletThree extends HttpServlet {
}
