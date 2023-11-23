<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="Homework1WebApp1.Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body style="height: 42px">
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="instructionsLabel" runat="server" Text="Develop a Web Site Application to consume the password and login id generation service developed(Same functionality as 2 above, but this time the client is a Web Site Application.) "></asp:Label>
        </div>
        <br />
        Type String Here:<asp:TextBox ID="stringBox" runat="server" style="margin-left: 20px" Width="366px"></asp:TextBox>
        <asp:Button ID="goButton" runat="server" OnClick="goButton_Click" style="margin-left: 48px" Text="Go" />
        <p>
            Reversed String:<asp:TextBox ID="rStringBox" runat="server" ReadOnly="True" style="margin-left: 28px" Width="364px"></asp:TextBox>
        </p>
        <p>
            Vowel Count:<asp:TextBox ID="vowelBox" runat="server" ReadOnly="True" style="margin-left: 43px" Width="176px"></asp:TextBox>
        </p>
        <p>
            Uppercase Count:<asp:TextBox ID="upperBox" runat="server" ReadOnly="True" style="margin-left: 16px" Width="177px"></asp:TextBox>
        </p>
        <p>
            Lowercase Count:<asp:TextBox ID="lowerBox" runat="server" ReadOnly="True" style="margin-left: 15px" Width="176px"></asp:TextBox>
        </p>
        <p>
            Digit Count:<asp:TextBox ID="digitBox" runat="server" ReadOnly="True" style="margin-left: 54px" Width="174px"></asp:TextBox>
        </p>
        <p>
            &nbsp;</p>
    </form>
</body>
</html>
