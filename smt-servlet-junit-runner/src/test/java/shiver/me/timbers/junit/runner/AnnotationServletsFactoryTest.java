package shiver.me.timbers.junit.runner;

import org.junit.Test;
import shiver.me.timbers.junit.runner.test.ServletOne;
import shiver.me.timbers.junit.runner.test.ServletThree;
import shiver.me.timbers.junit.runner.test.ServletTwo;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.test.Constants.SERVLETS;

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

        @shiver.me.timbers.junit.runner.annotation.Servlets({ServletOne.class, ServletTwo.class, ServletThree.class})
        class TestClass {
        }

        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        assertEquals(SERVLETS, servlets.getServlets());
    }

}
