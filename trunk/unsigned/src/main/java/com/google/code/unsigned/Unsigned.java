package com.google.code.unsigned;

import java.math.BigInteger;

/**
 * @author peter.lawrey
 */
public class Unsigned {
    private static final BigInteger BI_2_64 = BigInteger.ONE.shiftLeft(64);

    private Unsigned() {
    }

    public static byte add(byte b1, byte b2) {
        return (byte) (b1 + b2);
    }

    public static short add(short s1, short s2) {
        return (short) (s1 + s2);
    }

    public static int add(int i1, int i2) {
        return i1 + i2;
    }

    public static long add(long l1, long l2) {
        return l1 + l2;
    }

    public static byte minus(byte b1, byte b2) {
        return (byte) (b1 - b2);
    }

    public static short minus(short s1, short s2) {
        return (short) (s1 - s2);
    }

    public static int minus(int i1, int i2) {
        return i1 - i2;
    }

    public static long minus(long l1, long l2) {
        return l1 - l2;
    }

    public static byte multiply(byte b1, byte b2) {
        return (byte) (b1 * b2);
    }

    public static short multiply(short s1, short s2) {
        return (short) (s1 * s2);
    }

    public static int multiply(int i1, int i2) {
        return i1 * i2;
    }

    public static long multiply(long l1, long l2) {
        return l1 * l2;
    }

    public static byte divide(byte b1, byte b2) {
        return (byte) ((b1 & 0xFF) / (b2 & 0xFF));
    }

    public static short divide(short s1, short s2) {
        return (short) ((s1 & 0xFFFF) / (s2 & 0xFFFF));
    }

    public static int divide(int i1, int i2) {
        return (int) ((i1 & 0xFFFFFFFFL) / (i2 & 0xFFFFFFFFL));
    }

    public static long divide(long l1, long l2) {
        if (l1 >= 0 && l2 > 0)
            return l1 / l2;
        return toBigInteger(l1).divide(toBigInteger(l2)).longValue();
    }

    public static boolean eq(byte b1, byte b2) {
        return b1 == b2;
    }

    public static boolean eq(short s1, short s2) {
        return s1 == s2;
    }

    public static boolean eq(int i1, int i2) {
        return i1 == i2;
    }

    public static boolean eq(long l1, long l2) {
        return l1 == l2;
    }

    public static boolean ne(byte b1, byte b2) {
        return b1 != b2;
    }

    public static boolean ne(short s1, short s2) {
        return s1 != s2;
    }

    public static boolean ne(int i1, int i2) {
        return i1 != i2;
    }

    public static boolean ne(long l1, long l2) {
        return l1 != l2;
    }

    public static boolean gt(byte b1, byte b2) {
        return (b1 & 0xFF) > (b2 & 0xFF);
    }

    public static boolean gt(short s1, short s2) {
        return (s1 & 0xFFFF) > (s2 & 0xFFFF);
    }

    public static boolean gt(int i1, int i2) {
        return (i1 & 0xFFFFFFFFL) > (i2 & 0xFFFFFFFFL);
    }

    public static boolean gt(long l1, long l2) {
        return (l1 + Long.MIN_VALUE) > (l2 + Long.MIN_VALUE);
    }

    public static boolean ge(byte b1, byte b2) {
        return (b1 & 0xFF) >= (b2 & 0xFF);
    }

    public static boolean ge(short s1, short s2) {
        return (s1 & 0xFFFF) >= (s2 & 0xFFFF);
    }

    public static boolean ge(int i1, int i2) {
        return (i1 & 0xFFFFFFFFL) >= (i2 & 0xFFFFFFFFL);
    }

    public static boolean ge(long l1, long l2) {
        return (l1 + Long.MIN_VALUE) >= (l2 + Long.MIN_VALUE);
    }

    public static boolean lt(byte b1, byte b2) {
        return (b1 & 0xFF) < (b2 & 0xFF);
    }

    public static boolean lt(short s1, short s2) {
        return (s1 & 0xFFFF) < (s2 & 0xFFFF);
    }

    public static boolean lt(int i1, int i2) {
        return (i1 & 0xFFFFFFFFL) < (i2 & 0xFFFFFFFFL);
    }

    public static boolean lt(long l1, long l2) {
        return (l1 + Long.MIN_VALUE) < (l2 + Long.MIN_VALUE);
    }

    public static boolean le(byte b1, byte b2) {
        return (b1 & 0xFF) <= (b2 & 0xFF);
    }

    public static boolean le(short s1, short s2) {
        return (s1 & 0xFFFF) <= (s2 & 0xFFFF);
    }

    public static boolean le(int i1, int i2) {
        return (i1 & 0xFFFFFFFFL) <= (i2 & 0xFFFFFFFFL);
    }

    public static boolean le(long l1, long l2) {
        return (l1 + Long.MIN_VALUE) <= (l2 + Long.MIN_VALUE);
    }

    public static String asString(byte b) {
        return String.valueOf(b & 0xFF);
    }

    public static String asString(short s) {
        return String.valueOf(s & 0xFFFF);
    }

    public static String asString(int i) {
        return String.valueOf(i & 0xFFFFFFFFL);
    }

    public static String asString(long l) {
        return l >= 0 ? String.valueOf(l) : toBigInteger(l).toString();
    }

    public static byte parseUnsignedByte(String text) throws NumberFormatException {
        int i = Integer.parseInt(text);
        if (i < 0 || i > 255) throw new NumberFormatException("Number out of range " + text);
        return (byte) i;
    }

    public static short parseUnsignedShort(String text) throws NumberFormatException {
        int i = Integer.parseInt(text);
        if (i < 0 || i > 65535) throw new NumberFormatException("Number out of range " + text);
        return (short) i;
    }

    public static int parseUnsignedInt(String text) throws NumberFormatException {
        long i = Long.parseLong(text);
        if (i < 0 || i >= (1L << 32)) throw new NumberFormatException("Number out of range " + text);
        return (int) i;
    }

    public static long parseUnsignedLong(String text) throws NumberFormatException {
        BigInteger bi = new BigInteger(text);
        if (bi.compareTo(BigInteger.ZERO) < 0 || bi.compareTo(BI_2_64) >= 0)
            throw new NumberFormatException("Number out of range " + text);
        return bi.longValue();
    }

    public static BigInteger toBigInteger(byte b) {
        return BigInteger.valueOf(b & 0xFF);
    }

    public static BigInteger toBigInteger(short s) {
        return BigInteger.valueOf(s & 0xFFFF);
    }

    public static BigInteger toBigInteger(int i) {
        return BigInteger.valueOf(i & 0xFFFFFFFFL);
    }

    public static BigInteger toBigInteger(long l) {
        final BigInteger bi = BigInteger.valueOf(l);
        return l >= 0 ? bi : bi.add(BI_2_64);
    }
}
