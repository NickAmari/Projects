
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
   This program reads a file with double values, then sort the aray, and finally calculate some statistics.
*/
public class dataProcessing
{
   public static void main(String[] args) throws FileNotFoundException
   {
      // Read Integer values from a file the data array data[]

      Scanner console = new Scanner(System.in);
      System.out.print("Input file: ");
      String inputFileName = console.next();


      double[] data = new double[10];

      // Construct the Scanner and PrintWriter objects for reading and writing

      File inputFile = new File(inputFileName);
      Scanner in = new Scanner(inputFile);

      // Read the input and write the output

      double total = 0;
      int i = 0;

      while (in.hasNextDouble())
      {
         data[i] = in.nextDouble();
         i++;
      }

      in.close();


     // Sort the data array
     double temp;
     int size = data.length;
	 	boolean exchanged = true;
	 	for (i = 0; i < size && exchanged; i++)
	 	{
	 		exchanged = false;
	 		for (int j = size - 1; j >= i + 1; j--)
	 		{
	 			if (data[j] < data[j - 1])
	 			{
	 				temp = data[j];
	 				data[j] = data[j - 1];
	 				data[j-1] = temp;
	 				exchanged = true;
	 			}
	 		}
	 }


	      // calculate statistics
	      int length = data.length;
	      double med, var, sd, mean, sum, varsum;

	      sum = 0.0;
	      for ( i = 0; i < length; i++)
	      {
	           sum += data [ i ];
	      }
	      med   = data [ length / 2 ];
	      mean = sum / (double) length;

	      varsum = 0.0;
	      for ( i = 0; i < length; i++)
	      {
	           varsum = varsum  + ((data [ i ] - mean) * (data [ i ] - mean));
	      }
	      var = varsum / ( length - 1 );
	      sd  = Math.sqrt ( var );

	      System.out.println ("length:                   " + length);
	      System.out.println ("mean:                    " + mean);
	      System.out.println ("median:                 " + med);
	      System.out.println ("variance:                " + var);
          System.out.println ("standard deviation: " + sd);

   }


}