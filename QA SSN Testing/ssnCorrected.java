import java.util.Scanner;
import java.util.regex.*;
    /*
    The valid SSN (Social Security Number) must satisfy the following conditions:
    It should have 9 digits.
    It should be divided into 3 parts by hyphen (-).
    The first part should have 3 digits and should not be 000, 666, or between 900 and
    999.
    The second part should have 2 digits, and it should be from 01 to 99.
    The third part should have 4 digits, and it should be from 0001 to 9999.
    */
    public class h2qu2
    {
        public static void main(String args[])
        {
            Scanner scan = new Scanner(System.in);
            String ssn = scan.nextLine();
            System.out.println( validateSSN(ssn));
        };
        public static String validateSSN(String social)
        {
            String ssn = social;
            boolean p1=false, p2=false, p3=false;
            boolean p1Length=false, p2Length=false, p3Length=true;
            char delimiter = '-';
            int count = 0;
            // this is to validate the second portion of the SSN using a regular expression
            String regex_mid = "(?!00)\\d{2}";
            Pattern p = Pattern.compile(regex_mid);
            // check if SSN has three parts && if length is correct for each part
            for (int i = 0; i < ssn.length(); i++)
            {
                //EDITED PORTION
                if (ssn.charAt(i) == delimiter)
                {
                    if(i == 3) {
                        p1Length=true;
                    }
                    if(i == 6) {
                        p2Length=true;
                    }
                    count++;
                }
                if(i > 9) {
                    p3Length=false;
                }
                // EDIT FINISHED
            }
			
            // if parts are incorrect length, auto fail
			// New booleans check to make sure the length of every segment is correct before anything else.
            if (p1Length == false || p2Length == false || p3Length == true) {
                return "invalid ssn";
            }
			
			
            // if ssn has three parts
            if(count == 2)
            {
                // split the ssn into three parts
                String[] parts = ssn.split("-");
// convert each portion to numbers (int)
                int first=Integer.parseInt(parts[0]);
                int mid= Integer.parseInt(parts[1]);
                int last= Integer.parseInt(parts[2]);
// check the validity of each portion
                Matcher m = p.matcher(parts[1]);
                p2= m.matches();
                if(first != 000 && first != 666 &&    !
                        (first>= 900 && first <= 999)) // SUS
                {
                    p1 = true;
                }
                if(last>=1 && last <= 9999) // SUS last still has to be 0001, not just 1
                {
                    p3 = true;
                }
                if(p1 && p2 && p3)
                {
                    return "valid ssn";
                }else
                {
                    return "invalid ssn";
                }
            }else  // if ssn doesn't have three parts
            {
                return "Incorrect format";
            }
        }
    }