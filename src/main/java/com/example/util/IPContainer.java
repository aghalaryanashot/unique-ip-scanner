package com.example.util;

/**
 * Interface for a container that stores unique IPs.
 */
public interface IPContainer {
    void add(int ip);

    long countUnique();
}
