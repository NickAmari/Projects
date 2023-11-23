using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace Homework1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class Service1 : IService1
    {
        public string reverse(string str)
        {
            char[] charArray = str.ToCharArray();
            Array.Reverse(charArray);
            return new string(charArray);
        }

        public stringStatistics analyzeStr(string str)
        {
            int countU = 0;
            int countL = 0;
            int countD = 0;
            int countV = 0;
            stringStatistics stats = new stringStatistics();

            //counting Upper
            for (int i = 0; i < str.Length; i++)
            {
                if (char.IsUpper(str[i])) { countU++; }
            }

            //counting Lower
            for (int i = 0; i < str.Length; i++)
            {
                if (char.IsLower(str[i])) { countL++; }
            }

            //counting digits
            for (int i = 0; i < str.Length; i++)
            {
                if (char.IsDigit(str[i])) { countD++; }
            }

            //counting vowels
            for (int i = 0; i < str.Length; i++)
            {
                if (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u' ||
                    str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U')
                {
                    countV++;
                }
            }

            stats.UpperCount = countU;
            stats.LowerCount = countL;
            stats.DigitCount = countD;
            stats.VowelCount = countV;

            return stats;
        }
    }
}
