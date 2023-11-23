using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Homework1Forms
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            StringReference1.Service1Client myClient = new StringReference1.Service1Client("BasicHttpBinding_IService1");

            string temp = stringBox.Text;
            string revString = myClient.reverse(temp);

            revStringBox.Text = revString;

            vowelBox.Text = myClient.analyzeStr(temp).VowelCount.ToString();
            uppperBox.Text = myClient.analyzeStr(temp).UpperCount.ToString();
            lowerBox.Text = myClient.analyzeStr(temp).LowerCount.ToString();
            digitBox.Text = myClient.analyzeStr(temp).DigitCount.ToString();

        }
    }
}
