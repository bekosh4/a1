package edu.algods.cli;

import edu.algods.metrics.Metrics;
import edu.algods.util.CSVWriter;
import edu.algods.sort.MergeSort;
import edu.algods.sort.QuickSort;
import edu.algods.select.DeterministicSelect;
import edu.algods.geom.ClosestPair2D;
import edu.algods.geom.ClosestPair2D.Point;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

public class Main {
    private static final Random RNG = new Random();

    public static void main(String[] args) throws IOException {
        String out = args.length > 0 ? args[0] : "metrics.csv";
        try (CSVWriter csv = new CSVWriter(new File(out))) {
            // Sorting benchmarks
            for (int n : new int[]{1000, 5000, 10000, 20000}) {
                int[] base = randomArray(n);
                runSort(csv, "mergesort", base, () -> MergeSort.sort(base));
                runSort(csv, "quicksort", base, () -> QuickSort.sort(base));
            }
            // Select benchmarks
            for (int n : new int[]{1000, 5000, 10000, 20000}) {
                int[] a = randomArray(n);
                int k = n / 2;
                Metrics.reset();
                long t0 = System.currentTimeMillis();
                int val = DeterministicSelect.select(a, k);
                long t1 = System.currentTimeMillis();
                csv.writeLine(Metrics.current().toCSV(n, t1 - t0, "select_mom5"));
                if (n <= 5000) { // quick correctness check
                    int[] b = a.clone();
                    Arrays.sort(b);
                    if (b[k] != val) throw new AssertionError("Select mismatch");
                }
            }
            // Closest pair
            for (int n : new int[]{2000, 4000, 8000}) {
                Point[] pts = randomPoints(n);
                Metrics.reset();
                long t0 = System.currentTimeMillis();
                double d = ClosestPair2D.solve(pts);
                long t1 = System.currentTimeMillis();
                csv.writeLine(Metrics.current().toCSV(n, t1 - t0, "closest_pair"));
                if (!(d >= 0)) throw new AssertionError("Distance invalid");
            }
        }
    }

    private static void runSort(CSVWriter csv, String name, int[] base, Runnable r) throws IOException {
        int[] a = base.clone();
        Metrics.reset();
        long t0 = System.currentTimeMillis();
        r.run();
        long t1 = System.currentTimeMillis();
        csv.writeLine(Metrics.current().toCSV(a.length, t1 - t0, name));
        if (!isSorted(a)) throw new AssertionError(name + " failed");
    }

    private static int[] randomArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RNG.nextInt();
        return a;
    }

    private static Point[] randomPoints(int n) {
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) p[i] = new Point(RNG.nextDouble(), RNG.nextDouble());
        return p;
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }
}
