package com.example.io.reader;

import com.example.io.parser.IPParser;
import com.example.io.parser.IPv4Parser;
import com.example.util.BitSetContainer;
import com.example.util.IPContainer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BufferedInputIPReaderTest {

    private final IPReader reader = new BufferedInputIPReader();

    private long readAndCountUnique(File file) throws IOException {
        IPParser parser = new IPv4Parser();
        IPContainer container = new BitSetContainer(8);
        reader.read(file, parser, container);
        return container.countUnique();
    }

    @Test
    void testReadTwoTxt() throws IOException {
        File file = new File("src/test/resources/two.txt");
        assertTrue(file.exists(), "File two.txt should exist");

        long unique = readAndCountUnique(file);
        assertEquals(2, unique, "There should be exactly 2 unique IP addresses in two.txt");
    }

    @Test
    void testReadIpv4sTxt() throws IOException {
        File file = new File("src/test/resources/ipv4s.txt");
        assertTrue(file.exists(), "File ipv4s.txt should exist");

        long unique = readAndCountUnique(file);

        // The file contains 100+ IPs, most of them unique
        assertTrue(unique >= 90, "There should be at least 90 unique IP addresses in ipv4s.txt");
    }
}