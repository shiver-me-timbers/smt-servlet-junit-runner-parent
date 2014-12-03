package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.http.HttpServlet;

public class ServletTwo extends HttpServlet {
    public static final String NAME = ServletTwo.class.getSimpleName();
    public static final String URL = "/" + NAME;
}
