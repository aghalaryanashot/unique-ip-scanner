# Unique IP Scanner

A high-performance Java utility for scanning and counting **unique IPv4 addresses** from large text files.  
Supports two reading strategies: **BufferedInputStream** and **MappedByteBuffer**, configurable via CLI.

---

## Features

- Supports two high-performance file readers:
    - `MappedByteBuffer` (for memory-mapped IO, chunked by 1GB)
    - `BufferedInputStream` (classic stream-based reading)
- Byte-based IP parsing without `split()` for performance.
- Compact and efficient memory usage using `BitSet`-based containers.
- Easily extendable and testable.

---

## Project Structure

```
ipscanner/
├── main/
│   └── java/com/example/
│       ├── Main.java                   # CLI entry point
│       ├── core/IPScanner.java        # Core scanner logic
│       ├── io/
│       │    ├── parser/
│       │    │   ├── IPPaser.java
│       │    │   └── IPv4Paser.java
│       │    └── reader/                 # Reader implementations
│       │        ├── IPReader.java
│       │        ├── BufferedInputIPReader.java
│       │        └── MappedByteBufferIPReader.java
│       └── util/
│           ├── BitSetContainer.java
│           └── IPContainer.java
└── test/
    ├── java/com/example/
    │   ├── core/IPScannerTest.java        
    │   ├── io/
    │   │    ├── parser/
    │   │    │   └── IPv4PaserTest.java
    │   │    └── reader/                
    │   │        ├── BufferedInputIPReaderTest.java
    │   │        └── MappedByteBufferIPReaderTest.java
    │   └── util/
    │       └── BitSetContainerTest.java
    └── resources/
        ├── ip4s.txt
        └── two.txt
```

---

##  Usage

### Run with command line:

```bash
java -jar ipscanner.jar <file-path> <reader-type>
```

### Arguments:

| Argument     | Description                                            | Example                         |
|--------------|--------------------------------------------------------|---------------------------------|
| `<file-path>`| Path to the input file                                 | `/home/user/ip_addresses.txt`  |
| `<reader-type>` | File reader to use (`mapped` (default) or `buffered` ) | `buffered`                        |

### Example:

```bash
java -jar ipscanner.jar ./ip_addresses.txt mapped
```

---

## Running Tests

Unit tests for converter and container are located in the `test/` folder. Use any Java test runner (like JUnit) to run them.

---

## Building

If you're using Maven:

```bash
mvn clean package
```

Or with `javac`:

```bash
javac -d out src/ipscanner/*.java
jar --create --file ipscanner.jar --main-class=ipscanner.IPScannerApp -C out .
```

---

## Benchmark

| Reader Type     | File Size | Time (example) |
|------------------|-----------|----------------|
| BufferedInput    | 120 GB    | 20-26 m        |
| MappedByteBuffer | 120 GB    | 7-9 m          |

*Results depend on hardware and JVM options.*



