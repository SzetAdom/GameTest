package Repository;

import Modell.User;
import java.sql.Date;

public class UserRepo {

    public static Integer getUserByKey(String key) {
        if (key.equals("admin")) {
            return 1;
        } else {
            return 0;
        }
    }

    public static Boolean isUserAdmin(Integer id) {
        if (id % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean addNewUser(User user) {
        if (user.getKey().equals("newKey")) {
            return true;
        } else {
            return false;
        }
    }

    public static User getUser(Integer id) {
        return new User(id, "newUser", "Tesztelő Tamás", Date.valueOf("2021-04-27"), 1, Boolean.FALSE);
    }

    public static boolean updateUser(User user) {
        if (user.getUserId().equals(1)) {
            return true;
        } else {
            return false;
        }
    }
}
