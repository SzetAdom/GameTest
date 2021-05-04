package Repository;

import Modell.Database;
import Modell.Gender;
import Modell.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class UserRepo {

    public static Integer getUserByKey(String key) {
        try {            
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("Login");
            spq.registerStoredProcedureParameter("in_key", String.class, ParameterMode.IN);

            spq.setParameter("in_key", key);

            List<Object[]> userIds = spq.getResultList();
            int id = 0;
            for (Object[] currId : userIds) {
                id = Integer.parseInt(currId[0].toString());
                System.out.println("Id megtalálva!");
            }
            return id;
        } catch (Exception ex) {
            System.out.println("getUserByKey hiba! - " + ex.getMessage());
            return 0;
        }
    }

    public static Boolean isUserAdmin(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userIsAdmin");
            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", id);

            List<Object[]> userIsAdminObjectList = spq.getResultList();
            Boolean isAdmin = false;
            for (Object[] userIsAdminObject : userIsAdminObjectList) {
                isAdmin = Boolean.parseBoolean(userIsAdminObject[0].toString());
                System.out.println("Admin jog lekérdezve!");
            }
            return isAdmin;
        } catch (Exception ex) {
            System.out.println("getUserByKey hiba! - " + ex.getMessage());
            return null;
        }
    }

    public static String userCreate(User user) {
        try {
            EntityManager em = Database.getDbConn();
            /*StoredProcedureQuery spq = em.createStoredProcedureQuery("userCreate");
            if (user.getIsAdmin()) {
                spq = em.createStoredProcedureQuery("userCreateAdmin");
            }

            spq.registerStoredProcedureParameter("in_username", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_birth_date", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_gender_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_username", user.getUsername());
            spq.setParameter("in_birth_date", user.getBirthDate());
            spq.setParameter("in_gender_id", user.getGenderId().getGenderId());*/
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userCreateGeneral");
            
            spq.registerStoredProcedureParameter("in_isAdmin", Integer.class, ParameterMode.IN);
            
            spq.setParameter("in_isAdmin", user.getIsAdmin());
            
            spq.execute();
            
            List<Object[]> userObjectList = spq.getResultList();
            
            String key = userObjectList.get(0)[0].toString();
            
            System.out.println("Felhasználó sikeresen hozzáadva!");
            return key;

        } catch (Exception ex) {
            System.out.println("userAdd Hiba! - " + ex.getMessage());
            return "Hiba";
        }
    }

    public static User getUser(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userGet");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
            spq.setParameter("in_id", id);

            List<Object[]> userObjectList = spq.getResultList();
            User user = null;
            for (Object[] userObject : userObjectList) {
                Integer userId = Integer.parseInt(userObject[0].toString());
                String key = userObject[1].toString();
                String username = userObject[2].toString();
                Date birhtDate = Date.valueOf(userObject[3].toString());
                Gender gender = new Gender(Integer.parseInt(userObject[4].toString()), userObject[5].toString());
                Boolean isAdmin = Boolean.parseBoolean(userObject[6].toString());

                user = new User(id, key, username, birhtDate, gender, isAdmin);
            }
            System.out.println("User lekérdezve!");
            return user;
        } catch (Exception ex) {
            System.out.println("userGet hiba! - " + ex.getMessage());
            return null;
        }
    }

    public static boolean updateUser(User user) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userUpdate");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_username", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_birth_date", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_gender_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", user.getUserId());
            spq.setParameter("in_username", user.getUsername());
            spq.setParameter("in_birth_date", user.getBirthDate());
            spq.setParameter("in_gender_id", user.getGenderId().getGenderId());
            spq.execute();
            System.out.println("Felhasználó sikeresen frissítve!");
            return true;

        } catch (Exception ex) {
            System.out.println("userUpdate Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static boolean disableAdmin(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userDisableAdmin");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", id);

            spq.execute();
            System.out.println("Admin jogok sikeresen megvonva!");
            return true;

        } catch (Exception ex) {
            System.out.println("disableAdmin Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static boolean enableAdmin(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userEnableAdmin");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", id);

            spq.execute();
            System.out.println("Admin jogok sikeresen hozzáadva!");
            return true;

        } catch (Exception ex) {
            System.out.println("enableAdmin Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static boolean setUserActive(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userSetActive");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", id);

            spq.execute();
            System.out.println("User aktiv sikeres!");
            return true;

        } catch (Exception ex) {
            System.out.println("setUserActive Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static boolean setUserInactive(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userSetInactive");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", id);

            spq.execute();
            System.out.println("User inaktiv sikeres!");
            return true;

        } catch (Exception ex) {
            System.out.println("setUserInactive Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static List<User> getAllUser() {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userList");

            List<Object[]> userObjectList = spq.getResultList();
            List<User> userList = new ArrayList();
            for (Object[] userObject : userObjectList) {
                Integer id = Integer.parseInt(userObject[0].toString());
                String key = userObject[1].toString();
                String username = userObject[2].toString();
                Date birhtDate = Date.valueOf(userObject[3].toString());
                Gender gender = new Gender(Integer.parseInt(userObject[4].toString()), userObject[5].toString());
                Boolean isAdmin = Boolean.parseBoolean(userObject[6].toString());

                User user = new User(id, key, username, birhtDate, gender, isAdmin);
                userList.add(user);
            }
            System.out.println("Userek lekérdezve!");
            return userList;
        } catch (Exception ex) {
            System.out.println("getAllUser hiba! - " + ex.getMessage());
            return null;
        }
    }

}
