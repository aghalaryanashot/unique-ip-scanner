package com.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitSetContainerTest {

    @Test
    void testEmptyContainerShouldReturnZero() {
        BitSetContainer container = new BitSetContainer(4);
        assertEquals(0, container.countUnique(), "empty container return 0");
    }

    @Test
    void testSingleAddShouldReturnOne() {
        BitSetContainer container = new BitSetContainer(4);
        container.add(0x01020304); // int 16909060, ip 1.2.3.4
        assertEquals(1, container.countUnique(), "one IP return count = 1");
    }

    @Test
    void testDuplicateAddShouldNotIncreaseCount() {
        BitSetContainer container = new BitSetContainer(4);
        container.add(0x01020304); // int 16909060, ip 1.2.3.4
        container.add(0x01020304); // int 16909060, ip 1.2.3.4
        assertEquals(1, container.countUnique(), "duplicate ip don't change count");
    }

    @Test
    void testMultipleUniqueIps() {
        BitSetContainer container = new BitSetContainer(4);
        container.add(0x01020304); // int 16909060, ip 1.2.3.4
        container.add(0x01020305); // int 16909060, ip 1.2.3.5
        container.add(0xABCDEF01); // int -1412567039, ip 171.205.239.1
        assertEquals(3, container.countUnique(), "Three unique IPs");
    }

    @Test
    void testLevelOutOfBoundsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BitSetContainer(0));
        assertThrows(IllegalArgumentException.class, () -> new BitSetContainer(17));
    }
}