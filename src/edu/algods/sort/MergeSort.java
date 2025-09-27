package edu.algods.sort;

import edu.algods.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 20;

    public static void sort(int[] a) {
        int[] buf = new int[a.length];
        sort(a, buf, 0, a.length - 1, 1);
    }

    private static void sort(int[] a, int[] buf, int lo, int hi, long depth) {
        Metrics.current().enterFrame(depth);
        if (hi - lo + 1 <= CUTOFF) { InsertionSort.sort(a, lo, hi); return; }
        int mid = lo + (hi - lo) / 2;
        sort(a, buf, lo, mid, depth + 1);
        sort(a, buf, mid + 1, hi, depth + 1);
        if (cmp(a[mid], a[mid + 1]) <= 0) return; // already ordered
        merge(a, buf, lo, mid, hi);
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi) {
        int n = hi - lo + 1;
        System.arraycopy(a, lo, buf, lo, n);
        Metrics.current().move(n);
        Metrics.current().alloc(n);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (cmp(buf[i], buf[j]) <= 0) a[k++] = buf[i++];
            else a[k++] = buf[j++];
            Metrics.current().move(1);
        }
        while (i <= mid) { a[k++] = buf[i++]; Metrics.current().move(1); }
        while (j <= hi)  { a[k++] = buf[j++]; Metrics.current().move(1); }
    }

    private static int cmp(int x, int y) {
        Metrics.current().compare();
        return Integer.compare(x, y);
    }
}
