package edu.algods.util;

import edu.algods.metrics.Metrics;
import java.util.Random;

public class ArrayUtils {
    public static final Random RNG = new Random();

    public static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int t = a[i]; a[i] = a[j]; a[j] = t;
        Metrics.current().move(3);
    }

    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RNG.nextInt(i + 1);
            swap(a, i, j);
        }
    }

    public static int[] copyOf(int[] a) {
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        Metrics.current().move(a.length);
        Metrics.current().alloc(a.length);
        return b;
    }
}
