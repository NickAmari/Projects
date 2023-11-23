using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Homework1WebApp1
{
    public partial class Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void goButton_Click(object sender, EventArgs e)
        {
            ServiceReference1.Service1Client myClient = new ServiceReference1.Service1Client("BasicHttpBinding_IService1");

            string temp = stringBox.Text;
            string revString = myClient.reverse(temp);

            rStringBox.Text = revString;

            vowelBox.Text = myClient.analyzeStr(temp).VowelCount.ToString();
            upperBox.Text = myClient.analyzeStr(temp).UpperCount.ToString();
            lowerBox.Text = myClient.analyzeStr(temp).LowerCount.ToString();
            digitBox.Text = myClient.analyzeStr(temp).DigitCount.ToString();
        }
    }
}