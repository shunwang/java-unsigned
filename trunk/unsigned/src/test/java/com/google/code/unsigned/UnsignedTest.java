package com.google.code.unsigned;

import org.junit.Test;

import static com.google.code.unsigned.Unsigned.*;
import static junit.framework.Assert.assertEquals;

/**
 * @author peter.lawrey
 */
public class UnsignedTest {
    @Test
    public void testAdd() {
        assertEquals((byte) 250, add((byte) 200, (byte) 50));

        assertEquals((short) 25000, add((short) 20000, (short) 5000));

        assertEquals((int) 2500000000L, add((int) 2000000000, (int) 500000000));

        assertEquals("18446744073709551614", Unsigned.asString(add(Long.MAX_VALUE, Long.MAX_VALUE)));
    }

    @Test
    public void testMinus() {
        assertEquals((byte) 200, minus((byte) 250, (byte) 50));

        assertEquals((short) 20000, minus((short) 25000, (short) 5000));

        assertEquals(2000000000, minus((int) 2500000000L, (int) 500000000));

        assertEquals(Long.MAX_VALUE, minus(multiply(Long.MAX_VALUE, 2), Long.MAX_VALUE));
    }
}