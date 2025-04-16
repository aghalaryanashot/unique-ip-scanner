package com.example.core;
import com.example.io.reader.BufferedInputIPReader;
import com.example.io.reader.MappedByteBufferIPReader;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class IPScannerTest {

    @Test
    void testScanTwoTxtWithBufferedReader() throws Exception {
        File file = new File("src/test/resources/two.txt");
        assertTrue(file.exists(), "File two.txt should exist");

        IPScanner scanner = new IPScanner(new BufferedInputIPReader());
        long uniqueCount = scanner.scan(file);

        assertEquals(2, uniqueCount, "two.txt should contain 2 unique IP addresses");
    }

    @Test
    void testScanIpv4sTxtWithBufferedReader() throws Exception {
        File file = new File("src/test/resources/ipv4s.txt");
        assertTrue(file.exists(), "File ipv4s.txt should exist");

        IPScanner scanner = new IPScanner(new BufferedInputIPReader());
        long uniqueCount = scanner.scan(file);

        assertTrue(uniqueCount >= 90, "ipv4s.txt should contain at least 90 unique IP addresses");
    }

    @Test
    void testScanTwoTxtWithMappedReader() throws Exception {
        File file = new File("src/test/resources/two.txt");
        assertTrue(file.exists(), "File two.txt should exist");

        IPScanner scanner = new IPScanner(new MappedByteBufferIPReader());
        long uniqueCount = scanner.scan(file);

        assertEquals(2, uniqueCount, "two.txt should contain 2 unique IP addresses (mapped reader)");
    }

    @Test
    void testScanIpv4sTxtWithMappedReader() throws Exception {
        File file = new File("src/test/resources/ipv4s.txt");
        assertTrue(file.exists(), "File ipv4s.txt should exist");

        IPScanner scanner = new IPScanner(new MappedByteBufferIPReader());
        long uniqueCount = scanner.scan(file);

        assertTrue(uniqueCount >= 90, "ipv4s.txt should contain at least 90 unique IP addresses (mapped reader)");
    }
}