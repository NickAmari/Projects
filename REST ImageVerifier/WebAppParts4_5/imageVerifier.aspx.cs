using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Drawing;

namespace WebAppParts4_5
{
    public partial class imageVerifier : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Image1.ImageUrl = "~/imageProcess.aspx";
        }

        protected void imgButton_Click(object sender, EventArgs e)
        {
            imageReference1.ServiceClient myClient = new imageReference1.ServiceClient("BasicHttpBinding_IService");
            string userLength = lengthBox.Text;
            Session["userLength"] = userLength;
            string myStr = myClient.GetVerifierString(userLength);
            Session["generatedString"] = myStr;
            Image1.Visible = true;
            testBox.Text = myStr;
        }

        protected void submitButton_Click(object sender, EventArgs e)
        {
            if (inputBox.Text.Length > 0)
            {
                if (string.Equals(inputBox.Text, testBox.Text))
                {
                    congratsBox.Text = "Congrats! That is the correct string";
                }
                else
                {
                    congratsBox.Text = "Unfortunately, that is incorrect.";
                }
            }
        }
    }
}