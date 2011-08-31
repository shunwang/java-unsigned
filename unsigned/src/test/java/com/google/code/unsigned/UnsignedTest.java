/*
 * Copyright (c) 2011.  Peter Lawrey
 *
 * "THE BEER-WARE LICENSE" (Revision 128)
 * As long as you retain this notice you can do whatever you want with this stuff.
 * If we meet some day, and you think this stuff is worth it, you can buy me a beer in return
 * There is no warranty.
 */

package com.google.code.unsigned;

import org.junit.Test;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.Socket;

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

    assertEquals("18446744073709551614", asString(add(Long.MAX_VALUE, Long.MAX_VALUE)));
  }

  @Test
  public void testMinus() {
    assertEquals((byte) 200, minus((byte) 250, (byte) 50));

    assertEquals((short) 20000, minus((short) 25000, (short) 5000));

    assertEquals(2000000000, minus((int) 2500000000L, (int) 500000000));

    assertEquals(Long.MAX_VALUE, minus(multiply(Long.MAX_VALUE, 2), Long.MAX_VALUE));
  }

  public static void main(String... args) {
    Socket socket = null;
  }

  public static void writeBytes(DataOutput out, byte[] bytes) throws IOException {
    out.writeInt(bytes.length);
    out.write(bytes);
  }

  public static byte[] readBytes(DataInput in) throws IOException {
    int len = in.readInt();
    if (len < 0 || len > 1 << 24) throw new StreamCorruptedException("Invalid message length " + len);
    byte[] bytes = new byte[len];
    in.readFully(bytes);
    return bytes;
  }
}
