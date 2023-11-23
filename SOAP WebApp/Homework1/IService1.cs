using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace Homework1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {

        [OperationContract]
        string reverse(string str); // return origional string in reverse

        [OperationContract]
        stringStatistics analyzeStr(string str); // return stringStats object

        // TODO: Add your service operations here
    }


    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class stringStatistics
    {
        int upperCount = 0;
        int lowerCount = 0;
        int digitCount = 0;
        int vowelCount = 0;

        [DataMember]
        public int UpperCount
        {
            get { return upperCount; }
            set { upperCount = value; }
        }

        [DataMember]
        public int LowerCount
        {
            get { return lowerCount; }
            set { lowerCount = value; }
        }

        [DataMember]
        public int DigitCount
        {
            get { return digitCount; }
            set { digitCount = value; }
        }

        [DataMember]
        public int VowelCount
        {
            get { return vowelCount; }
            set { vowelCount = value; }
        }
    }
}
