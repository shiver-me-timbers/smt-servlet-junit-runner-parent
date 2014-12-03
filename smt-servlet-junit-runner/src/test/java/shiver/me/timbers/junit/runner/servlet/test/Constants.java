package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.ServletDetails;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.WebServletBuilder;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Constants {

    public static Servlets mockServlets() {

        final Servlets mock = mock(Servlets.class);
        when(mock.getServlets()).thenReturn(new ArrayList<ServletDetails>() {{
            add(
                    new ServletDetails(
                            WebServletBuilder.create().withName(ServletOne.NAME).withValue(ServletOne.URL).build(),
                            new ServletOne()
                    )
            );
            add(
                    new ServletDetails(
                            WebServletBuilder.create().withName(ServletTwo.NAME).withValue(ServletTwo.URL).build(),
                            new ServletTwo()
                    )
            );
            add(
                    new ServletDetails(
                            WebServletBuilder.create().withName(ServletThree.NAME).withUrlPatterns(ServletThree.URL)
                                    .build(),
                            new ServletThree()
                    )
            );
        }});
        return mock;
    }
}
