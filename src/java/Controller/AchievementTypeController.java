/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.AchievementType;
import Service.AchievementTypeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "AchievementTypeController", urlPatterns = {"/AchievementTypeController"})
public class AchievementTypeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("task").equals("addAchievementType")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("name").isEmpty()) {
                    try {
                        String name = request.getParameter("name");

                        Boolean serviceResult = AchievementTypeService.addAchievementType(name);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllAchievementType")) {
                JSONObject result = new JSONObject();
                try {
                    List<AchievementType> serviceResult = AchievementTypeService.getAllAchievementType();
                    result.put("result", serviceResult);

                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
            }

        } catch (Exception ex) {
            System.out.println("Hiányos mezők");
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
