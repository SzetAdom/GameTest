package Service;

import Modell.User;
import Repository.UserRepo;

public class UserService {

    public static Integer getUserByKey(String key) {
        return UserRepo.getUserByKey(key);
    }

    public static Boolean isUserAdmin(Integer id) {
        return UserRepo.isUserAdmin(id);
    }

    public static String addNewUser(User user) {
        if (UserRepo.addNewUser(user)) {
            return "Sikeres felhasználó hozzáadás!";
        } else {
            return "Sikertelen felhasználó hozzáadás!";
        }
    }

    public static User getUser(Integer id) {
        return UserRepo.getUser(id);
    }

    public static String updateUser(User user) {
        if (UserRepo.updateUser(user)) {
            return "Sikeres felhasználó update!";
        } else {
            return "Sikertelen felhasználó update!";
        }
    }

}
