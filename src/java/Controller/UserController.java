/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.User;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("task").equals("login")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("key").isEmpty()) {
                    try {
                        String key = request.getParameter("key");

                        Integer loginResult = UserService.getUserByKey(key);
                        Boolean isAdminResult = UserService.isUserAdmin(loginResult);
                        
/*
 *  TO ÁDÁM: a json objectet írasd ki, ne annak a stringjét :)
 *  ezt már kijavítottam, a többit nem néztem meg (by Roland)
 */

                        result.put("id", loginResult.toString());
                        result.put("isAdmin", isAdminResult.toString());
                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásánál");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("addUser")) {
                JSONObject returnValue = new JSONObject();
                if (!request.getParameter("key").isEmpty() && !request.getParameter("isAdmin").isEmpty()) {
                    try {
                        String key = request.getParameter("key");
                        Boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
                        User user = new User(key, isAdmin);

                        String result = UserService.addNewUser(user);
                        returnValue.put("result", result);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásánál");
                    }

                } else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(returnValue);
            }

            if (request.getParameter("task").equals("getUser")) {
                JSONObject returnValue = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        User user = UserService.getUser(id);
                        returnValue.put("result", user.toString());

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásánál");
                    }

                } else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(returnValue.toString());
            }

            if (request.getParameter("task").equals("updateUser")) {
                JSONObject returnValue = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String key = request.getParameter("key");
                        String username = request.getParameter("username");
                        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
                        Integer genderId = Integer.parseInt(request.getParameter("genderId"));
                        Boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

                        User user = new User(id, key, username, birthDate, genderId, isAdmin);
                        String result = UserService.updateUser(user);

                        returnValue.put("result", result);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásánál");
                    }

                } else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(returnValue.toString());
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
