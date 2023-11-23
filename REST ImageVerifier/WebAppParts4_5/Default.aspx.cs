using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebAppParts4_5
{
    public partial class Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void encryptButton_Click(object sender, EventArgs e)
        {
            encryptReference1.ServiceClient myClient = new encryptReference1.ServiceClient("BasicHttpBinding_IService");
            string temp = box1.Text;
            temp = myClient.Encrypt(temp);

            encryptedLabel.Text = temp;
        }

        protected void senderButton_Click(object sender, EventArgs e)
        {
            string temp = encryptedLabel.Text;
            box1.Text = "";
            encryptedLabel.Text = "Encrypted Message Shows Here";
            recMessage.Text = temp;
        }

        protected void decryptButton_Click(object sender, EventArgs e)
        {
            encryptReference1.ServiceClient myClient = new encryptReference1.ServiceClient("BasicHttpBinding_IService");
            string temp = recMessage.Text;
            temp = myClient.Decrypt(temp);
            decryptedMessage.Text = temp;
        }

        protected void switchButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("imageVerifier.aspx");
        }
    }
}