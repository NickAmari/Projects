<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="imageVerifier.aspx.cs" Inherits="WebAppParts4_5.imageVerifier" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="Label1" runat="server" Font-Bold="True" Font-Size="14pt" Text="Image Verifier:"></asp:Label>
            <br />
        </div>
        <asp:Label ID="imgLength" runat="server" Text="Insert Image String length:"></asp:Label>
        <asp:TextBox ID="lengthBox" runat="server" style="margin-left: 19px" Width="25px"></asp:TextBox>
        <asp:Button ID="imgButton" runat="server" OnClick="imgButton_Click" style="margin-left: 40px" Text="Generatre Image String" />
        <asp:TextBox ID="testBox" runat="server" Visible="False"></asp:TextBox>
        <br />
        <asp:Image ID="Image1" runat="server" Visible="False" />
        <p>
            <asp:Label ID="enterString" runat="server" Text="Enter String in Image Here:"></asp:Label>
            <asp:TextBox ID="inputBox" runat="server" style="margin-left: 15px" Width="285px"></asp:TextBox>
            <asp:Button ID="submitButton" runat="server" style="margin-left: 25px" Text="Go" Height="33px" OnClick="submitButton_Click" Width="88px" />
        </p>
        <asp:TextBox ID="congratsBox" runat="server" BorderStyle="None" ReadOnly="True" Width="427px"></asp:TextBox>
    </form>
</body>
</html>
