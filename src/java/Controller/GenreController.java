/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.Genre;
import Service.GenreService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "GenreController", urlPatterns = {"/GenreController"})
public class GenreController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("task").equals("addGenre")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("name").isEmpty()) {
                    try {
                        String name = request.getParameter("name");

                        Boolean serviceResult = GenreService.addGenre(name);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("updateGenre")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("name").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String name = request.getParameter("name");

                        Genre genre = new Genre(id, name);

                        Boolean serviceResult = GenreService.updateGenre(genre);
                        result.put("result", serviceResult);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllGenre")) {
                JSONObject result = new JSONObject();
                try {
                    List<Genre> serviceResult = GenreService.getAllGenre();
                    result.put("result", serviceResult);

                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setGenreActive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", GenreService.setGenreActive(id));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setGenreInactive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", GenreService.setGenreInactive(id));

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
