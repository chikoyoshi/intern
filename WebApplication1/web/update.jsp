<%-- 
    Document   : update
    Created on : 2017/08/18, 17:10:20
    Author     : intern2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./mycascade.css" type="text/css">
        <title>Update</title>
        <script type="text/javascript" src="myscript.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>   
        <p>データを更新します. <br> 更新するIDを入力してください.</p>
        <%
            if (request.getAttribute("message") != null){
                String str = request.getAttribute("message").toString();
                out.print("<script>");
                out.print("error(\"" + str + "\")");
                out.print("</script>");
            }            
        %>
            <form method="post" action="/WebApplication1/Update" id="update" onSubmit="return check_update()">
                <div>
                    <p>
                        <label for="update_id">
                            ID:
                        </label>
                        <input type="text" name="updateid" id="update_id">
                    </p>
                </div>               
                <div>
                    <p>
                        <label for="update_name">
                            Name:
                        </label>
                        <input type="text" name="updatename" id="update_name">
                    </p>
                </div>
                <div>
                    <p>
                        <label for="update_tel">
                            Tel:
                        </label>
                        <input type="text" name="updatetel" id="update_tel">
                    </p>
                </div>
                <div>
                    <p>
                        <input type="submit" value="更新する">
                    </p>
                </div>
            </form>
            <a href="result.jsp">
                <input type="submit" value="戻る" name="delete" />
            </a>
    </body>
</html>
