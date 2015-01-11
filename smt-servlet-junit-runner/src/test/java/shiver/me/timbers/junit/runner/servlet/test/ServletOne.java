package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import static shiver.me.timbers.junit.runner.servlet.test.Constants.SERVLET_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.SERVLET_DETAIL_ONE_PATH;

@WebServlet(name = SERVLET_DETAIL_ONE_NAME, value = SERVLET_DETAIL_ONE_PATH)
public class ServletOne extends HttpServlet {
}
