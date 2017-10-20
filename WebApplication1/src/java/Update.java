/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.DbHelper;
import entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author intern2
 */
@WebServlet(urlPatterns = {"/Update"})
public class Update extends HttpServlet {

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
            out.println("<title>Servlet Update</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Update at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher;
        DbHelper dh = new DbHelper();
        String update_id ;
        String update_name ;
        String update_tel ;
        boolean flag = false;
        
        try{
            if (request.getParameter("updateid").equals("") ||  request.getParameter("updatename").equals("") || request.getParameter("updatetel").equals("")){
                dispatcher = request.getRequestDispatcher("update.jsp");
                request.setAttribute("message", "空欄があります.");
                dispatcher.forward(request, response);
                return;
            }
        } catch(NullPointerException e){
                dispatcher = request.getRequestDispatcher("update.jsp");
                request.setAttribute("message", "空欄があります.");
                dispatcher.forward(request, response);
                return;
        }
        
        update_id = request.getParameter("updateid").toString();
        update_name = request.getParameter("updatename").toString();
        update_tel = request.getParameter("updatetel").toString();
        
        if (!update_id.matches("^\\d{4}$")) {
            request.setAttribute("message", "idに不正な値が入力されました.(数字4桁)");    
            flag = true;
        }
        if (!update_name.matches("^[0-9a-zA-Z]{1,10}$")){
            request.setAttribute("message", "nameに不正な値が入力されました.(半角英数字10文字まで.)");
            flag = true;
        }
        if (!update_tel.matches("^[0-9]{10,11}$")){
            request.setAttribute("message", "telに不正な値が入力されました.(数字9~10桁ハイフンなし)");
            flag = true;
        }
        
        if (flag) {
            dispatcher = request.getRequestDispatcher("update.jsp");
            dispatcher.forward(request, response);
        }      
        
        Date updateDate = new Date();

        Staff staff = new Staff();
        staff.setId(update_id);
        staff.setName(update_name);
        staff.setTel(update_tel);
        staff.setUpdateDate(updateDate);
        if (dh.update(staff) == false){
            request.setAttribute("message", update_id + "は登録されていません.");
            dispatcher = request.getRequestDispatcher("update.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
        //processRequest(request, response);
        dh.close();        
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
