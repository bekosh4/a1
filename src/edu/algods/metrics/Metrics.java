package edu.algods.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class Metrics {
    private static final ThreadLocal<Metrics> LOCAL = ThreadLocal.withInitial(Metrics::new);
    public static Metrics current() { return LOCAL.get(); }
    public static void reset() { LOCAL.set(new Metrics()); }

    public final AtomicLong comparisons = new AtomicLong();
    public final AtomicLong moves = new AtomicLong();
    public final AtomicLong allocations = new AtomicLong();
    public final AtomicLong recursionDepth = new AtomicLong();
    public final AtomicLong maxRecursionDepth = new AtomicLong();

    public void enterFrame(long depth) {
        recursionDepth.set(depth);
        maxRecursionDepth.set(Math.max(maxRecursionDepth.get(), depth));
    }

    public void compare() { comparisons.incrementAndGet(); }
    public void move(long k) { moves.addAndGet(k); }
    public void alloc(long k) { allocations.addAndGet(k); }

    public String toCSV(long n, long millis, String algo) {
        return String.join(",",
                algo, Long.toString(n), Long.toString(millis),
                Long.toString(comparisons.get()),
                Long.toString(moves.get()),
                Long.toString(allocations.get()),
                Long.toString(maxRecursionDepth.get()));
    }
}
