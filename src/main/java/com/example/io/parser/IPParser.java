package com.example.io.parser;

/**
 * Interface for converting bytes into integer IP addresses.
 */
public interface IPParser {
    Integer consume(int b);
}
