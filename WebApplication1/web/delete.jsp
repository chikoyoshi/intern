<%-- 
    Document   : delete
    Created on : 2017/08/18, 17:12:15
    Author     : intern2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <script type="text/javascript" src="myscript.js"></script>
        <title>Delete</title>
        <link rel="stylesheet" href="./mycascade.css" type="text/css"> 
    </head>
    <body>
        <jsp:include page="header.jsp"/>      
        <p>既存データを削除します.</p>
        <%
            if (request.getAttribute("message") != null){
                String str = request.getAttribute("message").toString();
                out.print("<script>");
                out.print("error(\"" + str + "\")");
                out.print("</script>");
            }
        %> 
        <br>
        <form method="post" action="/WebApplication1/Servlet5" id="delete" onsubmit="return check_delete()">
            <div>
                <p>
                    <label for="delete_id">
                        ID:
                    </label>
                    <input type="text" name="deleteid" id="delete_id">
                </p>
            </div>
            <br>
            <button type="submit" value="削除する">削除する</button>
        </form>
        <a href="result.jsp">
            <input type="submit" value="戻る" name="delete" />
        </a>
    </body>
</html>
