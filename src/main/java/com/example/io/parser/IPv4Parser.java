package com.example.io.parser;

import java.util.Arrays;

/**
 * Stateless IP address byte-to-int converter.
 * Not thread-safe, but safe for single-threaded use.
 */
public class IPv4Parser implements IPParser {
    private final int[] parts = new int[4];
    private int currentPartsIndex = 0;
    private int currentValue = 0;


    @Override
    public Integer consume(int byteData) {
        if (isDigit(byteData)) {
            return processDigit(byteData);
        } else if (byteData == '.') {
            return processDot();
        } else if (byteData == '\n' || byteData == -1) {
            return processEndOfData();
        }
        return null;
    }


    private Integer processDigit(int byteData)  {
        currentValue = currentValue * 10 + (byteData - '0');
        return null;
    }

    private Integer processDot() {
        parts[currentPartsIndex++] = currentValue;
        currentValue = 0;
        return null;
    }

    private Integer processEndOfData() {
        parts[currentPartsIndex] = currentValue;
        int ip = (parts[0] << 24) | (parts[1] << 16) | (parts[2] << 8) | parts[3];
        reset();
        return ip;
    }

    private boolean isDigit(int byteData) {
        return byteData >= '0' && byteData <= '9';
    }

    public void reset() {
        Arrays.fill(parts, 0);
        currentPartsIndex = 0;
        currentValue = 0;
    }
}
