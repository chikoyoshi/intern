/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Staff;
import Dao.DbHelper;
import entity.Lesson01;
import java.util.List;
import org.hibernate.HibernateException;


/**
 *
 * @author intern2
 */
@WebServlet(urlPatterns = {"/Servlet3"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet3</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet3 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usr;                 //入力されたユーザーID を格納
        String pass;                //入力されたパスワード を格納
        List<Lesson01> buf;                 //データベースからの結果一時格納
        int i = 0;
        /*
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        */
        try {
            /*
            String driver = "com.mysql.jdbc.Driver";      //JDBCドライバの登録
            //データベースの指定
            String server = "localhost";        //MySQLサーバ()
            String dbname = "test";           //データベース名
            String url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";             //ユーザ情報登録
            String password = "admin";
            Class.forName(driver);
            
            //データベースとの接続
            con = DriverManager.getConnection(url, user, password);
            */
            //データ取り出し
            usr = request.getParameter("usr");
            pass = request.getParameter("pass");
            
            //テーブル紹介実行
            /*
            stmt = con.createStatement();
            usr = "\"" + usr + "\"";
            pass = "\"" + pass +"\"";
            sql = "select count(*) from lesson01 where password = " + pass + " and userID = " +usr;          //ユーザID照会
            rs = stmt.executeQuery(sql);
            
            //テーブル紹介結果を出力
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
            if (count == 1){
                request.setAttribute("message","ログイン成功");
            }else{
                request.setAttribute("message","ログイン失敗");
            }
            */
            //Lesson01Helper lh = new Lesson01Helper();
            DbHelper dh = new DbHelper();
            buf = dh.isMatchAccount(usr, pass);
            if (buf != null){            
                request.getSession().setAttribute("user", buf.get(0).getUserName());              
                RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Login incorrect");
                RequestDispatcher dispatcher = request.getRequestDispatcher("fault.jsp");
                dispatcher.forward(request, response);
            }
        }/*catch(SQLException e){
            System.err.println("SQL failed");
            e.printStackTrace();
        } catch (ClassNotFoundException ex1) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
        }*/ catch (ServletException ex2){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex2);
        }catch (HibernateException ex3) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex3);
        }catch (Exception ex4){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex4);
        }
        //processRequest(request, response);
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int i = 0;
        String usr;
        if (request.getSession().getAttribute("usr") == null ){
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            usr = request.getSession().getAttribute("usr").toString();
            DbHelper dh = new DbHelper();
            if ( dh.searchData(usr) ){
                List<Staff> list = dh.searchAllStaff();
                request.setAttribute(("list_size"), list.size());
                for (Staff st: list){
                    i++;
                    request.setAttribute("id"+String.format("%04d", i), st.getId());
                    request.setAttribute("name"+String.format("%04d",i), st.getName());
                    request.setAttribute("tel"+String.format("%04d",i), st.getTel());
                    request.setAttribute("hire_date"+String.format("%04d",i),st.getHireDate());
                    request.setAttribute("update_date"+String.format("%04d",i), st.getUpdateDate());
                }
                dispatcher = request.getRequestDispatcher("result.jsp");
                dispatcher.forward(request, response);
            } else {
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        }
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
