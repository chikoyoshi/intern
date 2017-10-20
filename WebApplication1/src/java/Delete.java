/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.DbHelper;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author intern2
 */
@WebServlet(urlPatterns = {"/Servlet5"})
public class Delete extends HttpServlet {

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
            out.println("<title>Servlet Servlet5</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet5 at " + request.getContextPath() + "</h1>");
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
        String delete_id;
        DbHelper dh = new DbHelper();
        RequestDispatcher dispatcher;

        try{
            if (request.getParameter("deleteid").equals("")){
                dispatcher = request.getRequestDispatcher("delete.jsp");
                dispatcher.forward(request, response);
            }                
        }catch(NullPointerException e){
            dispatcher = request.getRequestDispatcher("delete.jsp");
            dispatcher.forward(request, response);
        }
        
        delete_id = request.getParameter("deleteid");        
        
        if (!delete_id.matches("^\\d{4}$")) {
            dispatcher = request.getRequestDispatcher("delete.jsp");
            dispatcher.forward(request, response);
            return;            
        }                
        
        delete_id = request.getParameter("deleteid");
        if (!dh.deleteData(delete_id)){
            request.setAttribute("message", delete_id + "は登録されていません.");
            dispatcher = request.getRequestDispatcher("delete.jsp");
            dispatcher.forward(request, response);
            return;
        }
                
        dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
        //processRequest(request, response);
        dh.close();
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
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
