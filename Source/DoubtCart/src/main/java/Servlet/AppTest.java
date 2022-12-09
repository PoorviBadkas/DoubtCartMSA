/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Client.DoubtAnswerInterface;
import Client.ResourceInterface;
import Client.UserInterface;
import Entity.Answer;
import Entity.Doubt;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author HP
 */
@WebServlet(name = "AppTest", urlPatterns = {"/AppTest"})
public class AppTest extends HttpServlet {

        @Inject @RestClient UserInterface UserApp;
        @Inject @RestClient DoubtAnswerInterface DApp;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
            @Inject @RestClient ResourceInterface a; 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AppTest</title>");            
            out.println("</head>");
            out.println("<body>");
            
//            Collection<Doubt> aaa = DApp.ListDoubts();
//            Collection<User> users = UserApp.AllUsers();
            Collection<Answer> answerss = DApp.SingleDoubt(5).getAnswerCollection();

            
            
//            out.println(aaa.toString());
//            out.println(answerss.toString());
//            a.updateResources(32, "test", "testDS", 8, "subTest", "915908350996_resource.png");
            out.println("Progress!!");
//            try
//            {
//                Collection<Comments> comm = a.Allcomments();
//                
//                out.println("<center>");
//                out.println("<table border='1' cellpadding='5' cellspacing='5'>");
//                out.println("<thead>");
//                out.println("<tr>");
//                out.println("<td>ID</td>");
//                out.println("<td>Comment</td>");
//                out.println("<td>ResourceID</td>");
//                out.println("<td>UserID</td>");
//                out.println("<td>Date</td>");
//                out.println("</tr>");
//                out.println("</thead>");
//                out.println("<tbody>");
//                
//                for(Doubt c:aaa)
//                {
//                        out.println("<tr>");
//                        out.println("<td>"+c.getTitle()+"</td>");
//                        out.println("<td>"+c.getDescription()+"</td>");                        
//                        out.println("</tr>");                    
//                }
//                
//                out.println("</tbody>");
//                out.println("<tfoot></tfoot>");
//                out.println("</table>");
//                out.println("</center>");
//            
//            }
//            catch(Exception e)
//            {
//                out.println("Something went wrong");
//                out.println("<br>");
//                out.println(e);
//            }          
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
