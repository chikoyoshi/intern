<%-- 
    Document   : new_entry.jsp
    Created on : 2017/08/18, 17:05:43
    Author     : intern2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./mycascade.css" type="text/css">
        <script type="text/javascript" src="myscript.js"></script>
        <title>New Entry</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <p>新規データを登録します.</p>      <br>
        <%
            if (request.getAttribute("message") != null){
                String str = request.getAttribute("message").toString();
                out.print("<script>");
                out.print("error(\"" + str + "\")");
                out.print("</script>");
            }
        %>        
        <form method="post" action="/WebApplication1/Servlet4" name="entry" onSubmit="return check_entry()">
            <div>
                <p>
                    <label for="new_id">
                        ID:
                    </label>
                    <input type="text" name="newid" id="new_id">
                </p>
            </div>
            <div>
                <p>
                    <label for="new_name">
                        名前:
                    </label>
                    <input type="text" name="newname" id="new_name">
                </p>
            </div>
            <div>
                <p>
                    <label for="new_tel">
                        tel:
                    </label>
                    <input type="text" name="newtel" id="new_tel">
                </p>
            </div>
            <br>
            <div>
                <input type="submit" value="登録する">
            </div>
        </form>
        <a href="result.jsp">
            <input type="submit" value="戻る" name="delete" />
        </a>
    </body>
</html>
