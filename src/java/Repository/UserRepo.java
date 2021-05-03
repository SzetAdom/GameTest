package Repository;

import Modell.Database;
import Modell.Gender;
import Modell.User;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

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

    public static boolean userCreate(User user) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userCreate");
            if (user.getIsAdmin()) {
                spq = em.createStoredProcedureQuery("userCreateAdmin");
            }

            spq.registerStoredProcedureParameter("in_username", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_birth_date", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_gender_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_username", user.getUsername());
            spq.setParameter("in_birth_date", user.getBirthDate());
            spq.setParameter("in_gender_id", user.getGenderId().getGenderId());
            spq.execute();
            System.out.println("Felhasználó sikeresen hozzáadva!");
            return true;

        } catch (Exception ex) {
            System.out.println("userAdd Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static User getUser(Integer id) {
        return new User(id, "newUser", "Teszt Tamás", Date.valueOf("2021-04-27"), new Gender(1), Boolean.FALSE);
    }

    public static boolean updateUser(User user) {
        if (user.getUserId().equals(1)) {
            return true;
        } else {
            return false;
        }
    }
}
