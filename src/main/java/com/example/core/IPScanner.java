package com.example.core;

import com.example.util.BitSetContainer;
import com.example.io.parser.IPParser;
import com.example.io.parser.IPv4Parser;
import com.example.io.reader.IPReader;

import java.io.File;

public class IPScanner {

    private final static int CONTAINER_BUCKET_LEVEL = 8; // 2^8 = 256 BitSet
    private final IPReader reader;
    
    public IPScanner(IPReader reader) {
        this.reader = reader;
    }
    
    public long scan(File file) throws Exception {
        BitSetContainer container = new BitSetContainer(CONTAINER_BUCKET_LEVEL);
        IPParser converter = new IPv4Parser();
        reader.read(file, converter, container);
        return container.countUnique();
    }
}