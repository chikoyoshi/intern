<%-- 
    Document   : header
    Created on : 2017/08/23, 14:23:47
    Author     : intern2
--%>

<p>
<%
    out.print("Welcome ");
    out.print(request.getSession().getAttribute("user"));
    out.print(" !");
%>
</p>

<form method="post" action="/WebApplication1/Logout">
    <input type="submit" value="logout" name="logout">
</form>