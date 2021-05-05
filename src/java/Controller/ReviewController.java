/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.ReviewService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Win10
 */
public class ReviewController extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("task").equals("getScoreDistribution")) {
                JSONObject result = new JSONObject();
                try {
                    JSONObject scoreDistribution = ReviewService.getScoreDistribution();
                    result.put("result", scoreDistribution);
                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
            }
            if (request.getParameter("task").equals("getReviewsOverTime")) {
                JSONObject result = new JSONObject();
                try {
                    JSONObject reviewsOverTime = ReviewService.getReviewsOverTime();
                    result.put("result", reviewsOverTime);
                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
            }
            if (request.getParameter("task").equals("reviewListbyUser")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        JSONArray reviewListbyUser = ReviewService.getReviewListbyUser(id);
                        result.put("result", reviewListbyUser);
                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }
                    out.println(result);
                }
            }
            
            if (request.getParameter("task").equals("reviewListbyGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        JSONArray reviewListbyGame = ReviewService.getReviewListbyGame(id);
                        result.put("result", reviewListbyGame);
                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }
                    out.println(result);
                }
            }
            
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
