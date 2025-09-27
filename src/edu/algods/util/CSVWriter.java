package edu.algods.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CSVWriter implements Closeable, Flushable {
    private final Writer w;
    public CSVWriter(File f) throws IOException {
        this.w = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
        this.w.write("algo,n,time_ms,comparisons,moves,allocations,depth\n");
    }
    public void writeLine(String line) throws IOException { w.write(line); w.write("\n"); }
    @Override public void flush() throws IOException { w.flush(); }
    @Override public void close() throws IOException { w.close(); }
}
