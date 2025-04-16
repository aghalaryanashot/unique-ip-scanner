package com.example.util;

import java.util.BitSet;

/**
 * BitSet-based IP container.
 * Segmented to support up to 2^32 unique IPs.
 */
public class BitSetContainer implements IPContainer {
    private final BitSet[] storage;
    private final int mask;
    private final int shift;

    public BitSetContainer(int level) {
        if (level < 1 || level > 16) {
            throw new IllegalArgumentException("Level must be between 1 and 16");
        }
        storage = new BitSet[1 << level];
        for (int i = 0; i < storage.length; i++) {
            storage[i] = new BitSet();
        }
        shift = Integer.SIZE - level;
        mask = 0xFFFFFFFF >>> level;
    }

    @Override
    public void add(int ip) {
        int idx = ip >>> shift;
        int bit = ip & mask;
        storage[idx].set(bit);
    }

    @Override
    public long countUnique() {
        long total = 0;
        for (BitSet bs : storage) {
            total += bs.cardinality();
        }
        return total;
    }
}
