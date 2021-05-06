/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.Game;
import Service.GameService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "GameController", urlPatterns = {"/GameController"})
public class GameController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("task").equals("gameCreate")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("name").isEmpty()
                        && !request.getParameter("description").isEmpty()
                        && !request.getParameter("dev").isEmpty()
                        && !request.getParameter("releaseDate").isEmpty()
                        && !request.getParameter("price").isEmpty()) {
                    try {
                        String name = request.getParameter("name");
                        String description = request.getParameter("description");
                        String dev = request.getParameter("dev");
                        Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
                        Integer price = Integer.parseInt(request.getParameter("price"));

                        Game game = new Game(name, description, dev, releaseDate, price);
                        Boolean serviceResult = GameService.gameCreate(game);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        ArrayList<String> game = GameService.getGame(id);
                        result.put("result", game.toString());

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllGame")) {
                JSONObject result = new JSONObject();
                try {
                    result.put("result", GameService.getAllGame());

                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("updateGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("name").isEmpty()
                        && !request.getParameter("description").isEmpty()
                        && !request.getParameter("dev").isEmpty()
                        && !request.getParameter("releaseDate").isEmpty()
                        && !request.getParameter("price").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("gameId"));
                        String name = request.getParameter("name");
                        String description = request.getParameter("description");
                        String dev = request.getParameter("dev");
                        Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
                        Integer price = Integer.parseInt(request.getParameter("price"));

                        Game game = new Game(id, name, description, dev, releaseDate, price);
                        Boolean serviceResult = GameService.updateGame(game);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setGameActive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", GameService.setGameActive(id));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setGameInactive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", GameService.setGamInactive(id));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
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
