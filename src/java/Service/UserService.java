package Service;

import Modell.User;
import Repository.UserRepo;
import java.util.ArrayList;
import java.util.List;
import Modell.Game;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserService {

    public static Integer getUserByKey(String key) {
        return UserRepo.getUserByKey(key);
    }

    public static Boolean isUserAdmin(Integer id) {
        return UserRepo.isUserAdmin(id);
    }

    public static String addNewUser(User user) {
        System.out.println("------------------------");
        System.out.println("addNewUser");
        return UserRepo.userCreate(user);
    }

    public static ArrayList<String> getUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getUser");
        return UserRepo.getUser(id);
    }

    public static Boolean updateUser(User user) {
        System.out.println("------------------------");
        System.out.println("updateUser");
        if (UserRepo.updateUser(user)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean disableAdmin(Integer id) {
        System.out.println("------------------------");
        System.out.println("disableAdmin");

        return UserRepo.disableAdmin(id);
    }

    public static Boolean enableAdmin(Integer id) {
        System.out.println("------------------------");
        System.out.println("enableAdmin");

        return UserRepo.enableAdmin(id);
    }

    public static Boolean setUserActive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setUserActive");

        return UserRepo.setUserActive(id);
    }

    public static Boolean setUserInactive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setUserInactive");

        return UserRepo.setUserInactive(id);
    }

    public static List<User> getAllUser() {
        System.out.println("------------------------");
        System.out.println("getAllUser");

        return UserRepo.getAllUser();
    }
    
    public static List<Game> getGamesByUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getGamesByUser");

        return UserRepo.getGamesByUser(id);
    }
    
    public static JSONObject getTestersOverTime() {
        System.out.println("------------------------");
        System.out.println("getTestersOverTime");

        return UserRepo.getTestersOverTime();
    }
     

}
