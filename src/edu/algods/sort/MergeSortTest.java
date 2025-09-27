package edu.algods.sort;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    public void randomArrays() {
        Random rng = new Random(1);
        for (int n : new int[]{0,1,2,3,10,100,1000}) {
            int[] a = rng.ints(n).toArray();
            int[] b = a.clone();
            MergeSort.sort(a);
            java.util.Arrays.sort(b);
            assertArrayEquals(b, a);
        }
    }
}
