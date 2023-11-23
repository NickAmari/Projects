<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="WebAppParts4_5.Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">
        #Text1 {
            width: 374px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="senderLabel" runat="server" Font-Bold="True" Font-Size="14pt" Text="Sender:"></asp:Label>
            <asp:TextBox ID="switchBox" runat="server" BorderStyle="None" style="margin-left: 704px" Width="176px">Switch To Image Verification:</asp:TextBox>
            <asp:Button ID="switchButton" runat="server" OnClick="switchButton_Click" style="margin-left: 16px" Text="Switch" Width="57px" />
            <br />
            <br />
            Type message to be encrypted here:</div>
        <p>
            <asp:TextBox ID="box1" runat="server" Width="377px"></asp:TextBox>
            <asp:Button ID="encryptButton" runat="server" OnClick="encryptButton_Click" style="margin-left: 33px" Text="Encrypt" />
        </p>
        <asp:Label ID="encryptedLabel" runat="server" Text="Encrypted Message Shows here"></asp:Label>
        <br />
        <br />
        This will send message to reciever:
        <asp:Button ID="senderButton" runat="server" OnClick="senderButton_Click" style="margin-left: 40px" Text="Send" Width="55px" />
        <p>
            ______________________________________________________________________________________________________________________________________________________________________________________________</p>
        <p>
            <asp:Label ID="recieverLabel" runat="server" Font-Bold="True" Font-Size="14pt" Text="Receiver:"></asp:Label>
        </p>
        <p>
            Recived Message:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Label ID="recMessage" runat="server"></asp:Label>
        </p>
        <asp:Button ID="decryptButton" runat="server" OnClick="decryptButton_Click" Text="Decrypt" />
        <p>
            <asp:Label ID="decryptedMessage" runat="server" Text="Decrypted Message here"></asp:Label>
        </p>
    </form>
</body>
</html>
