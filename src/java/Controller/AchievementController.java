/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.Achievement;
import Service.AchievementService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "AchievementController", urlPatterns = {"/AchievementController"})
public class AchievementController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("task").equals("createAchievement")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("gameId").isEmpty()
                        && !request.getParameter("description").isEmpty()
                        && !request.getParameter("typeId").isEmpty()) {
                    try {
                        Integer gameId = Integer.parseInt(request.getParameter("gameId"));
                        String description = request.getParameter("description");
                        Integer prerequisite = null;
                        if (!request.getParameter("prerequisite").isEmpty()) {
                            prerequisite = Integer.parseInt(request.getParameter("prerequisite"));
                        }
                        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
                        Achievement achievement = new Achievement(gameId, description, prerequisite, typeId);

                        Boolean serviceResult = AchievementService.createAchievement(achievement);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAchievement")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        Achievement serviceResult = AchievementService.getAchievement(id);

                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllAchievement")) {
                JSONObject result = new JSONObject();
                try {

                    List<Achievement> serviceResult = AchievementService.getAllAchievement();

                    result.put("result", serviceResult);

                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllAchievementByGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        List<Achievement> serviceResult = AchievementService.getAllAchievementByGame(id);

                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllAchievementByUser")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        List<Achievement> serviceResult = AchievementService.getAllAchievementByUser(id);

                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllAchievementByUserGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("gameId").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Integer gameId = Integer.parseInt(request.getParameter("gameId"));

                        List<Achievement> serviceResult = AchievementService.getAllAchievementByUser_Game(id, gameId);

                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setAchievementActive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        Boolean serviceResult = AchievementService.setAchievementActive(id);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setAchievementInactive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        Boolean serviceResult = AchievementService.setAchievementInactive(id);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("updateAchievement")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("gameId").isEmpty()
                        && !request.getParameter("description").isEmpty()
                        && !request.getParameter("typeId").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Integer gameId = Integer.parseInt(request.getParameter("gameId"));
                        String description = request.getParameter("description");
                        Integer prerequisite = null;
                        if (!request.getParameter("prerequisite").isEmpty()) {
                            prerequisite = Integer.parseInt(request.getParameter("prerequisite"));
                        }
                        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
                        Achievement achievement = new Achievement(id, gameId, description, prerequisite, typeId);

                        Boolean serviceResult = AchievementService.updateAchievement(achievement);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }
        } catch (Exception ex) {
            System.out.println("Hiányos mezők - " + ex.getMessage());
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
