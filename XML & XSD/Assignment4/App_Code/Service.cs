using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Xml;
using System.Xml.Schema;
using System.Xml.XPath;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service" in code, svc and config file together.
public class Service : IService
{
    static string ret = "";
    public string verification(string xml, string xmls)
    {
        XmlReaderSettings xmlDoc = new XmlReaderSettings();
        xmlDoc.Schemas.Add(xml, xmls);
        xmlDoc.ValidationType = ValidationType.Schema;
        xmlDoc.ValidationEventHandler += new ValidationEventHandler(xmlValidationEventHandler);

        XmlReader xmlw = XmlReader.Create(xml, xmlDoc);

        while (xmlw.Read()) { }

        if (ret != "")
        {
            return ret;
        }

        return "No Error";
    }
    static void xmlValidationEventHandler(object sender, ValidationEventArgs e)
    {
        if (e.Severity == XmlSeverityType.Error)
        {
            ret = "ERROR: " + e.Message;
        }
    }

    public string xPathSearch(string xml, string pathExpression)
    {
        string data = "";
        XPathDocument dx = new XPathDocument(xml);
        XPathNavigator nav = dx.CreateNavigator();
        XPathNodeIterator iterator = nav.Select(pathExpression);

        while (iterator.MoveNext())
        {
            XPathNodeIterator it = iterator.Current.Select("Name");
            it.MoveNext();
            data += it.Current.Value + " ";
        }

        if (data != "")
        {
            return data;
        }

        return "Path Expression Not Found";
    }
}
