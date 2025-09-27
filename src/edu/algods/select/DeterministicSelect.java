package edu.algods.select;

import edu.algods.metrics.Metrics;
import edu.algods.sort.InsertionSort;

public class DeterministicSelect {

    public static int select(int[] a, int k) {
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("k");
        return select(a, 0, a.length - 1, k, 1);
    }

    private static int select(int[] a, int lo, int hi, int k, long depth) {
        Metrics.current().enterFrame(depth);
        int n = hi - lo + 1;
        if (n <= 5) { InsertionSort.sort(a, lo, hi); return a[lo + k]; }


        int m = 0;
        for (int i = lo; i <= hi; i += 5) {
            int r = Math.min(i + 4, hi);
            InsertionSort.sort(a, i, r);
            int med = i + (r - i) / 2;
            swap(a, lo + m, med); m++;
        }
        int mom = select(a, lo, lo + m - 1, m / 2, depth + 1);
        int p = partitionAroundValue(a, lo, hi, mom);
        int left = p - lo;
        if (k == left) return a[p];
        if (k < left) return select(a, lo, p - 1, k, depth + 1);
        return select(a, p + 1, hi, k - left - 1, depth + 1);
    }

    private static int partitionAroundValue(int[] a, int lo, int hi, int pivotValue) {
        int pivotIndex = lo;
        for (int i = lo; i <= hi; i++) if (a[i] == pivotValue) { pivotIndex = i; break; }
        swap(a, pivotIndex, hi);
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            Metrics.current().compare();
            if (a[j] < pivot) { swap(a, i, j); i++; }
        }
        swap(a, i, hi);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int t = a[i]; a[i] = a[j]; a[j] = t;
        Metrics.current().move(3);
    }
}
