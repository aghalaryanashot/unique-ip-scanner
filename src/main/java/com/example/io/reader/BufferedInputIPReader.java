package com.example.io.reader;

import com.example.util.IPContainer;
import com.example.io.parser.IPParser;

import java.io.*;

/**
 * BufferedInputStream-based IP reader for single-threaded sequential reading.
 */
public class BufferedInputIPReader implements IPReader {
    @Override
    public void read(File file, IPParser converter, IPContainer container) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            int b;
            while ((b = is.read()) != -1) {
                Integer ip = converter.consume(b);
                if (ip != null) {
                    container.add(ip);
                }
            }
        }
    }
}
