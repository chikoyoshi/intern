<%-- 
    Document   : index
    Created on : 2017/08/14, 16:38:12
    Author     : intern2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./mycascade.css" type="text/css">
        <title>Login</title>
    </head>
    <body>
        <form method="post" action="/WebApplication1/Servlet3">
            <div class="fel">
                <p>
                    <label for="ft_un">
                        ID:
                    </label>
                    <input type="text" name="usr" size="40" id="ft_un">
                </p>
            </div>
            <div class="fel">
                <p>
                    <label for="ft_ut">
                        パスワード:
                    </label>
                    <input type="password" name="pass" size="40" id="ft_ut">
                </p>
            </div>
            <br>
            <div class="fel">
                <button type="submit" class="fly" value="login">
                    <p>submit</p>
                </button>
            </div>
        </form>
    </body>
</html>
