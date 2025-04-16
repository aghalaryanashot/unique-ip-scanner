package com.example.io.reader;

import com.example.util.IPContainer;
import com.example.io.parser.IPParser;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer-based IP reader. Reads large files in 1GB chunks using memory-mapped IO.
 */
public class MappedByteBufferIPReader implements IPReader {
    private static final long CHUNK_SIZE = 1L << 30; // 1GB

    @Override
    public void read(File file, IPParser converter, IPContainer container) throws IOException {
        try (FileChannel channel = new RandomAccessFile(file, "r").getChannel()) {
            long fileSize = channel.size();
            long position = 0;

            while (position < fileSize) {
                long remaining = fileSize - position;
                long size = Math.min(remaining, CHUNK_SIZE);
                MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, position, size);

                while (buffer.hasRemaining()) {
                    int b = buffer.get() & 0xFF;
                    Integer ip = converter.consume(b);
                    if (ip != null) {
                        container.add(ip);
                    }
                }
                position += size;
            }
        }
    }
}
