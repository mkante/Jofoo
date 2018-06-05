package io.kante.jofoo.util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by moh on 8/8/16.
 */
public class ArrayHelpersTest
{

    @Test
    public void merge() {

        Integer[] A = {1,2,3};
        Object[] c = ArrayHelpers.merge(A, 4,5);
        assertTrue(c.length == 5);
        assertTrue((Integer)c[0] == 1);
        assertTrue((Integer)c[1] == 2);
        assertTrue((Integer)c[2] == 3);
        assertTrue((Integer)c[3] == 4);
        assertTrue((Integer)c[4] == 5);
    }

    @Test
    public void split() {

        String str1 = " mike,susan;song, mike";
        String[] parts = ArrayHelpers.split(str1, ",");
        assertTrue(parts.length == 3);
        assertEquals(parts[0], "mike");
        assertEquals(parts[1], "susan;song");
        assertEquals(parts[2], "mike");

    }
}
