public class bubble_sort {
    public static void main(String[] args) {

        int[] a = {1, 2, 4, 56, 7, 8, 9, 12, 13, 8};
        int size = 0;
        bubbleSortEfficient(a, size);
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");

    }
    public static void bubbleSortEfficient(int A[], int size)
    {
        int temp;
        boolean exchanged = true;
        for (int i = 0; i < size && exchanged; i++)
        {
            exchanged = false;
            for (int j = size - 1; j >= i + 1; j--)
            {
                if (A[j] < A[j - 1])
                {
                    temp = A[j];
                    A[j] = A[j - 1];
                    A[j-1] = temp;
                    exchanged = true;
                }
            }
        }

    }
}
