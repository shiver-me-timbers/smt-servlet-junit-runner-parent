package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Constants {

    public static final List<Class<? extends Servlet>> SERVLETS = unmodifiableList(
            new ArrayList<Class<? extends Servlet>>() {{
                add(ServletOne.class);
                add(ServletTwo.class);
                add(ServletThree.class);
            }}
    );
}
