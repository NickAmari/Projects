<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">
        #Text1 {
            width: 285px;
        }
        #Text2 {
            width: 255px;
        }
        #Text3 {
            width: 269px;
        }
        #Text4 {
            width: 281px;
        }
        #xmlURL1 {
            width: 330px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <b>XML Verification:</b>
            <br />
            <br />
            xml url
            <asp:TextBox ID="TextBox1" runat="server" Width="345px"></asp:TextBox>
            <br />
            xmls / xsd url :
            <asp:TextBox ID="TextBox2" runat="server" Width="363px"></asp:TextBox>
            <br />
            <br />
            <asp:Button ID="valButton" runat="server" Text="Verify" OnClick="valButton_Click" />
            <br />
            <br />
            Verification:
            <asp:Label ID="VerificationLabel" runat="server" Text="[Vlabel]"></asp:Label>
        <br />
        </div>
        <div>
            <b>XPathSearch:</b>
            <br />
            <br />
            xml url :<asp:TextBox ID="TextBox3" runat="server" Width="329px"></asp:TextBox>
            <br />
            <br />
            XPath expression :
            <asp:TextBox ID="TextBox4" runat="server" Width="298px"></asp:TextBox>
            <br />
            <br />
            <asp:Button ID="Button2" runat="server" Text="Search" OnClick="Button2_Click" />
            <br />
            <br />
            Path :
            <asp:Label ID="pathLabel" runat="server" Text="[Plabel]"></asp:Label>
        </div>
    </form>
</body>
</html>
