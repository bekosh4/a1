package edu.algods.sort;

import org.junit.jupiter.api.Test;
import edu.algods.metrics.Metrics;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void randomAndAdversarial() {
        Random rng = new Random(2);
        for (int n : new int[]{0,1,2,3,10,100,1000}) {
            int[] a = rng.ints(n).toArray();
            int[] b = a.clone();
            QuickSort.sort(a);
            java.util.Arrays.sort(b);
            assertArrayEquals(b,a);
        }
        int[] dup = new int[1000];
        java.util.Arrays.fill(dup, 7);
        QuickSort.sort(dup);
        assertTrue(java.util.Arrays.stream(dup).allMatch(x -> x==7));
    }

    @Test
    public void depthBoundTypical() {
        Random rng = new Random(3);
        int n = 10000;
        int[] a = rng.ints(n).toArray();
        Metrics.reset();
        QuickSort.sort(a);
        long depth = Metrics.current().maxRecursionDepth.get();
        long bound = (long)(2*Math.floor(Math.log(n)/Math.log(2)) + 20);
        assertTrue(depth <= bound, "depth="+depth+" bound="+bound);
    }
}
