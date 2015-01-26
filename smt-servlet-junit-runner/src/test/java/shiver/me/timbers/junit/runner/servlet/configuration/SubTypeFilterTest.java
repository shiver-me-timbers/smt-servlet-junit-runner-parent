package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.Lists;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.junit.runner.servlet.test.Lists.toList;

public class SubTypeFilterTest {

    @Test
    public void Subtypes_can_be_filtered_from_a_list_of_classes() throws Exception {

        // Given
        final List<Class> classes = Lists.<Class>toList(
                Object.class,
                Integer.class,
                String.class,
                TestOne.class,
                TestTwo.class,
                TestThree.class
        );

        final List<Class<? extends TestOne>> expected = toList(
                TestOne.class,
                TestTwo.class,
                TestThree.class
        );


        // When
        final List<Class<? extends TestOne>> actual = new SubTypeFilter<>(TestOne.class).create(classes);

        // Then
        assertEquals(expected, actual);
    }

    private interface TestOne {
    }

    private interface TestTwo extends TestOne {
    }

    private interface TestThree extends TestTwo {
    }
}
