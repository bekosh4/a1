package edu.algods.sort;

import edu.algods.metrics.Metrics;

public class InsertionSort {
    public static void sort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int v = a[i];
            int j = i - 1;
            while (j >= lo && cmp(v, a[j]) < 0) {
                a[j + 1] = a[j];
                Metrics.current().move(1);
                j--;
            }
            a[j + 1] = v;
            Metrics.current().move(1);
        }
    }
    private static int cmp(int x, int y) {
        Metrics.current().compare();
        return Integer.compare(x, y);
    }
}
