using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using System.Drawing.Imaging;

namespace WebAppParts4_5
{
    public partial class imageProcess : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Response.Clear();
            imageReference1.ServiceClient myClient = new imageReference1.ServiceClient("BasicHttpBinding_IService");
            string myStr, userLen;
            if (Session["generatedString"] == null)
            {
                if ( Session["userLength"] == null)
                {
                    userLen = "3";
                }
                else
                {
                    userLen = Session["userLength"].ToString();
                }
                myStr = myClient.GetVerifierString(userLen);
                Session["generatedString"] = myStr;
             }
            else
            {
                myStr = Session["generatedString"].ToString();
            }
            Stream myStream = myClient.GetImage(myStr);
            System.Drawing.Image myImage = System.Drawing.Image.FromStream(myStream);
            Response.ContentType = "image/jpeg";
            myImage.Save(Response.OutputStream, ImageFormat.Jpeg);
        }
    }
}