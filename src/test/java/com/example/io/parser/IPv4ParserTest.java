package com.example.io.parser;

import com.example.io.parser.IPParser;
import com.example.io.parser.IPv4Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPv4ParserTest {

    private int ipToInt(int a, int b, int c, int d) {
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    private Integer feed(IPParser parser, String ipText) {
        Integer result = null;
        for (char ch : ipText.toCharArray()) {
            result = parser.consume(ch);
        }
        return result;
    }

    @Test
    void testSimpleIp() {
        IPv4Parser parser = new IPv4Parser();
        int expected = ipToInt(192, 168, 0, 1);
        assertEquals(expected, feed(parser, "192.168.0.1\n"));
    }

    @Test
    void testAnotherIp() {
        IPv4Parser parser = new IPv4Parser();
        int expected = ipToInt(10, 0, 0, 42);
        assertEquals(expected, feed(parser, "10.0.0.42\n"));
    }

    @Test
    void testMaxIp() {
        IPv4Parser parser = new IPv4Parser();
        int expected = ipToInt(255, 255, 255, 255);
        assertEquals(expected, feed(parser, "255.255.255.255\n"));
    }

    @Test
    void testLeadingZeros() {
        IPv4Parser parser = new IPv4Parser();
        int expected = ipToInt(1, 2, 3, 4);
        assertEquals(expected, feed(parser, "001.002.003.004\n"));
    }

    @Test
    void testIncompleteInputReturnsNull() {
        IPv4Parser parser = new IPv4Parser();
        String partial = "127.0.0.1"; // no \n
        Integer result = feed(parser, partial);
        assertNull(result, "return null, if doesn't '\\n'");
    }
}