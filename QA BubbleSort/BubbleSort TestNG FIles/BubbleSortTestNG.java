import static org.testng.AssertJUnit.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import org.testng.annotations.Test;

public class BubbleSortTestNG {

    @Test
    public void T1() {
        int[] a = {1, 2, 4, 56, 7, 8, 9, 12, 13, 8};
        int size = 10;
        BubbleSort.bubbleSortEfficient(a, size);
        assertArrayEquals(new int[]{1,2,4,7,8,8,9,12,13,56}, a);
    }

    @Test
    public void T3() {
        int[] a = {1, 2, 4, 56, 7, 8, 9, 12, 13, 8};
        int size = 0;
        BubbleSort.bubbleSortEfficient(a, size);
        assertArrayEquals(new int[]{1,2,4,56,7,8,9,12,13,8}, a);
    }

    @Test
    public void T2() {
        int[] a = {1, 2, 4, 56, 7, 8, 9, 12, 13, 8};
        int size = 1;
        BubbleSort.bubbleSortEfficient(a, size);
        assertArrayEquals(new int[]{1,2,4,56,7,8,9,12,13,8}, a);
    }
}