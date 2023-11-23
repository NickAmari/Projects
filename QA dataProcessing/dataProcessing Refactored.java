
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


/**
 This program reads a file with double values, then sort the array, and finally calculate some statistics.
 */
public class DataProcessing
{
    public static void main(String[] args) throws FileNotFoundException {
        // Read Integer values from a file the data array data[]

        Scanner console = new Scanner(System.in);
        System.out.print("Input file: ");
        String inputFileName = console.next();

        console.close();

        List<Double> data = new ArrayList<>(); // Easier to work with data when importing lists

        // Construct the Scanner and PrintWriter objects for reading and writing
        Scanner in = null;
        try {
            File inputFile = new File(inputFileName);
            in = new Scanner(inputFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
            return;
        }

        // Read the input and write the output

        while (in.hasNext()) {
            if (in.hasNextDouble()) {
                data.add(in.nextDouble());
            } else {
                in.next();
            }
        }

        in.close();
        //Check if there is any data
        if (!data.isEmpty()) {
            // Sort the data array
            Collections.sort(data); //Easier to sort data when importing Lists
        }

        // calculate statistics
        int length = data.size();
        double med, var, sd, mean, sum, varsum;

        sum = 0.0;
        /*for ( int i = 0; i < length; i++)
        {
            sum += data [ i ]; OLD, DOESNT WORK WITH ARRAY LIST
        }*/
        for (Double bit : data) {
            sum = sum + bit; // Standard arrayList calc
        }

        //med   = data [ length / 2 ]; // cannot use array access operator
        med = data.get(length / 2);
        mean = sum / length;

        varsum = 0.0;

        for (int i = 0; i < length; i++) {
            varsum = varsum + ((data.get(i) - mean) * (data.get(i) - mean));
        }
        var = varsum / (length - 1);
        sd = Math.sqrt(var);

        System.out.println("length:                   " + length);
        System.out.println("mean:                    " + mean);
        System.out.println("median:                 " + med);
        System.out.println("variance:                " + var);
        System.out.println("standard deviation: " + sd);

    }

}
