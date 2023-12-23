package br.com.mmmsieto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Assertivas {

    @Test
    void assertivas() {
        // assertTrue
        // assertFalse
        // assertEquals
        // assertNotEquals
        // assertNull
        // assertNotNull
        // assertSame
        // assertNotSame
        // assertArrayEquals

        assertEquals("a", "a");
        assertNotEquals("a", "b");
        assertTrue(true);
        assertTrue("casa".contains("a"));
        assertTrue("casa".equalsIgnoreCase("CASA"));
        assertTrue("casa".startsWith("ca"));
        assertTrue("casa".endsWith("sa"));
        assertTrue("casa".contains("a"));
        assertFalse(false);
        assertNull(null);
        assertNotNull(new Object());
        assertSame("a", "a");
        assertNotSame("a", "b");
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        assertNotSame(s1, s2);
        assertNotSame(s1, s3);
        assertNotSame(s2, s3);

        assertSame(s1, s1);

        assertArrayEquals(s1.toArray(), s2.toArray());

        assertEquals(s1, s2);
        assertNotEquals(s1, s3);
        assertNotEquals(s2, s3);


    }

}
