package com.example;

import com.example.core.IPScanner;
import com.example.io.reader.BufferedInputIPReader;
import com.example.io.reader.IPReader;
import com.example.io.reader.MappedByteBufferIPReader;

import java.io.File;

/**
 * Main application class for scanning unique IP addresses.
 */
public class Main {
    final static String READER_TYPE_BUFFERED = "mapped";

    public static void main(String[] args) throws Exception {
        System.out.println("Start scan IP addresses ...");
        String readerType = null;
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java -jar unique-ip-scanner-1.0-SNAPSHOT.jar <file-path> " +
                    "or java -jar unique-ip-scanner-1.0-SNAPSHOT.jar <file-path> <reader-type: mapped|buffered>");
            return;
        }

        String filePath = args[0];
        if (args.length == 2) {
            readerType = args[1].toLowerCase();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File does not exist: " + filePath);
            return;
        }

        IPReader reader =
                READER_TYPE_BUFFERED.equals(readerType) ? new BufferedInputIPReader() : new MappedByteBufferIPReader();
        IPScanner scanner = new IPScanner(reader);
        long count = scanner.scan(file) ;

        System.out.println("Unique IP addresses: " + count);
    }
}

