using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void valButton_Click(object sender, EventArgs e)
    {
        string url = @"http://localHost:53654/Service.svc/verify?xml=" + TextBox1.Text + "&xmls=" + TextBox2.Text;
        HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
        WebResponse resp = req.GetResponse();
        Stream dataStream = resp.GetResponseStream();
        StreamReader reader = new StreamReader(dataStream);
        var responseReader = reader.ReadToEnd();
        resp.Close();
        string validation = JsonConvert.DeserializeObject<string>(responseReader);
        VerificationLabel.Text = validation;
    }

    protected void Button2_Click(object sender, EventArgs e)
    {
        string url = @"http://localHost:53654/Service.svc/xpath?xml=" + TextBox3.Text + "&exp=" + TextBox4.Text;
        HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
        WebResponse resp = req.GetResponse();
        Stream dataStream = resp.GetResponseStream();
        StreamReader reader = new StreamReader(dataStream);
        var responseReader = reader.ReadToEnd();
        resp.Close();
        string pathV = JsonConvert.DeserializeObject<string>(responseReader);
        pathLabel.Text = pathV;
    }
}