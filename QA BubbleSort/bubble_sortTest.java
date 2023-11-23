import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class bubble_sortTest {

    @Test
    void bubbleSortEfficient() {
        int[] a = {1, 2, 4, 56, 7, 8, 9, 12, 13, 8};
        int size = 10;
        bubble_sort.bubbleSortEfficient(a, size);
        assertArrayEquals(new int[]{1,2,4,7,8,8,9,12,13,56}, a);
    }

    @Test
    void bubbleSortEfficient2() {
        int[] a = {1, 2, 4, 56, 7, 8, 9, 12, 13, 8};
        int size = 0;
		bubble_sort.bubbleSortEfficient(a, size);
        assertArrayEquals(new int[]{1,2,4,56,7,8,9,12,13,8}, a);
    }

    @Test
    void bubbleSortEfficient3() {
        int[] a = {1, 2, 4, 56, 7, 8, 9, 12, 13, 8};
        int size = 1;
		bubble_sort.bubbleSortEfficient(a, size);
        assertArrayEquals(new int[]{1,2,4,56,7,8,9,12,13,8}, a);
    }
}