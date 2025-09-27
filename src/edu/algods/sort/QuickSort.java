package edu.algods.sort;

import edu.algods.metrics.Metrics;
import edu.algods.util.ArrayUtils;

public class QuickSort {

    public static void sort(int[] a) {
        ArrayUtils.shuffle(a); // randomized pivot
        sortRange(a, 0, a.length - 1, 1);
    }

    private static void sortRange(int[] a, int lo, int hi, long depth) {
        while (lo < hi) {
            Metrics.current().enterFrame(depth);
            int p = partition(a, lo, hi);
            int left = p - lo, right = hi - p;
            if (left < right) {
                if (lo < p - 1) sortRange(a, lo, p - 1, depth + 1); // recurse smaller
                lo = p + 1; // iterate larger
            } else {
                if (p + 1 < hi) sortRange(a, p + 1, hi, depth + 1);
                hi = p - 1;
            }
        }
    }

    private static int cmp(int x, int y) { Metrics.current().compare(); return Integer.compare(x, y); }

    private static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (cmp(a[j], pivot) <= 0) { ArrayUtils.swap(a, i, j); }
            if (cmp(a[j], pivot) <= 0) i++;
        }
        ArrayUtils.swap(a, i, hi);
        return i;
    }
}
