package com.example.io.reader;

import com.example.util.IPContainer;
import com.example.io.parser.IPParser;

import java.io.File;
import java.io.IOException;

/**
 * Interface for reading IP addresses from a file.
 */
public interface IPReader {
    void read(File file, IPParser converter, IPContainer container) throws IOException;
}
