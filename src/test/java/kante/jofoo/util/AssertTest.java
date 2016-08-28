package kante.jofoo.util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by moh on 8/15/16.
 */
public class AssertTest
{
    @Test
    public void notNull() {

        try {
            Assert.notNull(null, "some_is_wrong");
            assertTrue(true);
        }
        catch (Error e) {
            assertEquals(e.getMessage(), "some_is_wrong");
        }

        Assert.notNull(1, "some_is_wrong");
        assertTrue(true);
    }

    @Test
    public void isTrue() {

        try {
            Assert.isTrue(false, "is_not_true");
            assertTrue(true);
        }
        catch (Error e) {
            assertEquals(e.getMessage(), "is_not_true");
        }

        Assert.isTrue(true, "some_is_wrong");
        assertTrue(true);
    }

    @Test
    public void required() {

        try {
            Assert.required(null, "its_null");
            assertTrue(true);
        }
        catch (Error e) {
            assertEquals(e.getMessage(), "its_null");
        }

        try {
            Assert.required("  ", "its_empty");
            assertTrue(true);
        }
        catch (Error e) {
            assertEquals(e.getMessage(), "its_empty");
        }

        Assert.required("nice", "some_is_wrong");
        assertTrue(true);
    }
}
