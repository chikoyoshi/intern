<%-- 
    Document   : result.jsp
    Created on : 2017/08/15, 11:25:54
    Author     : intern2
--%>

<%@page import="Dao.DbHelper"%>
<%@page import="entity.Staff"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./mycascade.css" type="text/css">
        <title>Result Page</title>     
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <br>
        <a href="new_entry.jsp">
            <input type="submit" value="新規登録" name="new_enty"/>
        </a>
        <a href="update.jsp">
            <input type="submit" value="更新する" name="update" />
        </a>
        <a href="delete.jsp">
            <input type="submit" value="削除する" name="delete" />
        </a>
        <table class="table_sample">
            <tr>
                <th>
                    id
                </th>
                <th>
                    name
                </th>
                <th>
                    tel
                </th>
                <th>
                    hire_date
                </th>
                <th>
                    update_date
                </th>
            </tr>
            <%
                int page_num = 1;
                String pageid = request.getParameter("pageid");
                if ((pageid != null) && (pageid.matches("\\d")))
                    page_num = Integer.parseInt(pageid);
                DbHelper dh = new DbHelper();
                List<Staff> list = dh.searchLimitedStaff(page_num);
                out.println("現在のページ　" + page_num + "             ");
                out.println("<br>");
                out.println(((page_num- 1)*20)+1 + "件から" + page_num*20 + "件までを表示中");
                for (Staff st : list){
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(st.getId());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(st.getName());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(st.getTel());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(st.getHireDate());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(st.getUpdateDate());
                    out.println("</td>");                    
                    out.println("</tr>");                        
                }
            %>
        </table>
        <table>
            <tr>
                <%
                    int num = dh.howManyColumn();
                    if ((num%20) != 0){
                        num = (num/20) + 1;
                    } else {
                        num = num / 20;
                    }
                    for (int i=1;i<=num;i++){
                        out.println("<td>");
                        if ((page_num != i))
                            out.println("<a href=\"result.jsp?pageid="+i+"\">");
                        out.println(i);
                        if ((page_num != i))
                            out.println("</a>");
                        out.println("</td>");
                    }
                %>
            </tr>
        </table>
    </body>
</html>
