package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.SERVLETS;

public class AnnotationServletsFactoryTest {

    @Test
    public void No_servlets_are_returned_if_none_are_configured() {

        class TestClass {
        }

        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        assertThat(servlets.getServlets(), empty());
    }

    @Test
    public void Servlets_are_returned_if_some_are_configured() {

        @shiver.me.timbers.junit.runner.servlet.annotation.Servlets({ServletOne.class, ServletTwo.class, ServletThree.class})
        class TestClass {
        }

        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        assertEquals(SERVLETS, servlets.getServlets());
    }

}
