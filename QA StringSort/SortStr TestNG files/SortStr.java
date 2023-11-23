public class SortStr {
    public static String sortStr(String str)
    {

        char char_array[] = str.toCharArray();
        char temp;

        int i = 0;
        while (i < char_array.length) {
            int j = i + 1;
            while (j < char_array.length) {
                if (char_array[j] < char_array[i]) {
                    temp = char_array[i];
                    char_array[i] = char_array[j];
                    char_array[j] = temp;
                }
                j += 1;
            }
            i += 1;
        }
        String returnStr = String.valueOf(char_array);
        return returnStr;

    }
}
