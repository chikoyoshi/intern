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
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author intern2
 */
@WebServlet(urlPatterns = {"/Servlet4"})
public class NewEntry extends HttpServlet {

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
            out.println("<title>Servlet Servlet4</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet4 at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("new_entry.jsp");
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
        String new_id, new_name, new_tel;
        boolean flag = false;
        Date hire_date, update;
        RequestDispatcher dispatcher;
        
        if ((request.getParameter("newid") == null) || (request.getParameter("newname") == null) || (request.getParameter("newtel") == null)){
            dispatcher = request.getRequestDispatcher("new_entry.jsp");
            request.setAttribute("message", "値が入力されませんでした.");
            dispatcher.forward(request, response);
            return;
        }
        
        if (request.getParameter("newid").equals("") || request.getParameter("newname").equals("") || request.getParameter("newtel").equals("")){
            dispatcher = request.getRequestDispatcher("new_entry.jsp");
            request.setAttribute("message", "値が入力されませんでした.");
            dispatcher.forward(request, response);
            return;
        }
        
        new_id = request.getParameter("newid");
        new_name = request.getParameter("newname");
        new_tel = request.getParameter("newtel");
        
        if (!new_id.matches("^\\d{4}$")) {
            request.setAttribute("message", "idに不正な値が入力されました.(数字4桁)");    
            flag = true;
        }
        if (!new_name.matches("^[0-9a-zA-Z]{1,10}$")){
            request.setAttribute("message", "nameに不正な値が入力されました.(半角英数字10文字まで.)");
            flag = true;
        }
        if (!new_tel.matches("^[0-9]{10,11}$")){
            request.setAttribute("message", "telに不正な値が入力されました.(数字10~11桁ハイフンなし)");
            flag = true;
        }
        
        if (flag) {
            dispatcher = request.getRequestDispatcher("new_entry.jsp");
            dispatcher.forward(request, response);
        }
        
        hire_date = new Date();
        update = new Date();
        
        Staff staff = new Staff();
        
        staff.setId(new_id);
        staff.setName(new_name);
        staff.setTel(new_tel);
        staff.setHireDate(hire_date);
        staff.setUpdateDate(update);
        DbHelper dh = new DbHelper();
        try {
            dh.save(staff);
        } catch (ConstraintViolationException e){
            request.setAttribute("message", new_id+"は既に登録されています.");
            dispatcher = request.getRequestDispatcher("new_entry.jsp");
            dispatcher.forward(request, response);
        }
        
        request.setAttribute("message", "welcome");
        dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
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
