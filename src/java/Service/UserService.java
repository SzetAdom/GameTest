package Service;

import Modell.User;
import Repository.UserRepo;
import java.util.List;

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
        if (UserRepo.userCreate(user)) {
            return "Sikeres felhasználó hozzáadás!";
        } else {
            return "Sikertelen felhasználó hozzáadás!";
        }
    }

    public static User getUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getUser");
        return UserRepo.getUser(id);
    }

    public static String updateUser(User user) {
        System.out.println("------------------------");
        System.out.println("updateUser");
        if (UserRepo.updateUser(user)) {
            return "Sikeres felhasználó update!";
        } else {
            return "Sikertelen felhasználó update!";
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

}
