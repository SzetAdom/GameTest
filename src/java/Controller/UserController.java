/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.Gender;
import Modell.User;
import Modell.Game;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("task").equals("login")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("key").isEmpty()) {
                    try {
                        String key = request.getParameter("key");

                        Integer loginResult = UserService.getUserByKey(key);
                        Boolean isAdminResult = UserService.isUserAdmin(loginResult);

                        result.put("id", loginResult.toString());
                        result.put("isAdmin", isAdminResult.toString());
                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("userCreate")) {
                JSONObject result = new JSONObject();
                try {
                        Boolean isAdmin = false;

                        if (!request.getParameter("isAdmin").isEmpty()) {
                            isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
                        }

                        User user = new User(null, null, null, isAdmin);

                        String serviceResultString = UserService.addNewUser(user);
                        result.put("result", serviceResultString);

                } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                        result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getUser")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        ArrayList<String> user = UserService.getUser(id);
                        result.put("id", user.get(0));
                        result.put("key", user.get(1));
                        result.put("username", user.get(2));
                        result.put("birthDate", user.get(3));
                        result.put("gender", user.get(4));
                        result.put("isAdmin", user.get(5));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("updateUser")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("username").isEmpty()
                        && !request.getParameter("birthDate").isEmpty()
                        && !request.getParameter("genderId").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String username = request.getParameter("username");
                        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
                        Gender gender = new Gender(Integer.parseInt(request.getParameter("genderId")));

                        User user = new User(id, username, birthDate, gender);
                        Boolean serviceResultString = UserService.updateUser(user);

                        result.put("result", serviceResultString);

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("enableAdmin")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", UserService.enableAdmin(id));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("disableAdmin")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", UserService.disableAdmin(id));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setUserActive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", UserService.setUserActive(id));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("setUserInactive")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        result.put("result", UserService.setUserInactive(id));

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllUser")) {
                JSONObject result = new JSONObject();
                try {
                    result.put("result", UserService.getAllUser());

                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
            }
            
            if (request.getParameter("task").equals("userListGames")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        List<Game> games = UserService.getGamesByUser(id);
                        JSONArray jsonArray = new JSONArray();
                        for(Game game : games){
                            JSONObject gameJson = new JSONObject();
                            gameJson.put("id", game.getGameId());
                            gameJson.put("name", game.getName());
                            jsonArray.put(gameJson);
                        }
                        
                        result.put("result", jsonArray);
                        //result.put("id", loginResult.toString());
                        //result.put("isAdmin", isAdminResult.toString());
                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.println(result);
            }
            if (request.getParameter("task").equals("getTestersOverTime")) {
                JSONObject result = new JSONObject();
                try {
                    JSONObject testersOverTime = UserService.getTestersOverTime();

                    result.put("result", testersOverTime);
                    //result.put("id", loginResult.toString());
                    //result.put("isAdmin", isAdminResult.toString());
                } catch (Exception e) {
                    System.out.println("Hiba a JSON adatok beolvasásakor!");
                }
                out.println(result);
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
