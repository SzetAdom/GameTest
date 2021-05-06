package Repository;

import Modell.Database;
import Modell.Gender;
import Modell.User;
import Modell.Game;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserRepo {

    public static Integer getUserByKey(String key) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("Login");
                spq.registerStoredProcedureParameter("in_key", String.class, ParameterMode.IN);

                spq.setParameter("in_key", key);

                List<Object[]> userIds = spq.getResultList();
                int id = 0;
                for (Object[] currId : userIds) {
                    id = Integer.parseInt(currId[0].toString());
                    System.out.println("Id megtalálva!");
                }

                em.close();
                return id;
            } catch (Exception ex) {
                em.close();
                System.out.println("getUserByKey hiba! - " + ex.getMessage());
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return 0;
        }

    }

    public static Boolean isUserAdmin(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("userIsAdmin");
                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                List<Object[]> userIsAdminObjectList = spq.getResultList();
                Boolean isAdmin = false;
                for (Object[] userIsAdminObject : userIsAdminObjectList) {
                    isAdmin = Boolean.parseBoolean(userIsAdminObject[0].toString());
                    System.out.println("Admin jog lekérdezve!");
                }
                em.close();
                return isAdmin;
            } catch (Exception ex) {
                em.close();
                System.out.println("getUserByKey hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static String userCreate(User user) {
        try {
            EntityManager em = Database.getDbConn();
            try {

                StoredProcedureQuery spq = em.createStoredProcedureQuery("userCreate");
                

                spq.registerStoredProcedureParameter("in_isAdmin", Integer.class, ParameterMode.IN);
                if(user.getIsAdmin())spq.setParameter("in_isAdmin", 1);
                else spq.setParameter("in_isAdmin", 0);
                spq.execute();
                
                List<Object[]> userObjectList = spq.getResultList();
                String response = "";
                for(Object[] userObject : userObjectList){
                    response = userObject[0].toString();
                }
                System.out.println(userObjectList);
                em.close();
                System.out.println("Felhasználó sikeresen hozzáadva!");
                return response;

            } catch (Exception ex) {
                em.close();
                System.out.println("userAdd Hiba! - " + ex.getMessage());
                return "";
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return "";
        }

    }

    public static ArrayList<String> getUser(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("userGet");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.setParameter("in_id", id);

                List<Object[]> userObjectList = spq.getResultList();
                User user = null;
                ArrayList<String> list = new ArrayList<>();
                for (Object[] userObject : userObjectList) {
                    Integer userId = Integer.parseInt(userObject[0].toString());                    
                    String key = userObject[1].toString();
                    String username = userObject[2].toString();
                    Date birhtDate = Date.valueOf(userObject[3].toString());
                    Gender gender = new Gender(Integer.parseInt(userObject[4].toString()), userObject[5].toString());
                    Boolean isAdmin = Boolean.parseBoolean(userObject[6].toString());
                    list.add(userId.toString());
                    list.add(key);
                    list.add(username);
                    list.add(userObject[3].toString());
                    list.add(gender.toString());
                    list.add(isAdmin.toString());

                    user = new User(id, key, username, birhtDate, gender, isAdmin);
                }

                em.close();
                System.out.println("User lekérdezve!");
                return list;
            } catch (Exception ex) {
                em.close();
                System.out.println("userGet hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }

    }

    public static Boolean updateUser(User user) {
        try {

            EntityManager em = Database.getDbConn();
            try {
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

                em.close();
                
                System.out.println("Felhasználó sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("userUpdate Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean disableAdmin(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("userDisableAdmin");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                System.out.println("Admin jogok sikeresen megvonva!");
                return true;

            } catch (Exception ex) {
                em.close();
                System.out.println("disableAdmin Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean enableAdmin(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("userEnableAdmin");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Admin jogok sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("enableAdmin Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean setUserActive(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("userSetActive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("User aktiv sikeres!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("setUserActive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean setUserInactive(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("userSetInactive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("User inaktiv sikeres!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("setUserInactive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static List<User> getAllUser() {
        try {

            EntityManager em = Database.getDbConn();
            try {
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
                em.close();
                
                System.out.println("Userek lekérdezve!");
                return userList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllUser hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
        

    }
    public static List<Game> getGamesByUser(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("userListGames");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);
                
                List<Object[]> gameObjectList = spq.getResultList();
                List<Game> gameList = new ArrayList();
                for (Object[] gameObject : gameObjectList) {
                    Integer gameId = Integer.parseInt(gameObject[0].toString());
                    String name = gameObject[1].toString();
                    
                    Game game = new Game(gameId, name);
                    gameList.add(game);
                }
                em.close();
                
                System.out.println("Userek játékai lekérdezve!");
                return gameList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllUser hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }
    
    public static JSONObject getTestersOverTime() {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("testersOverTime");
                
                List<Object[]> testersOverTimeObjectList = spq.getResultList();
                
                JSONObject testersOverTime = new JSONObject();
                
                JSONArray yearArray = new JSONArray();
                JSONArray monthArray = new JSONArray();
                JSONArray numberOfArray = new JSONArray();
                for (Object[] testersOverTimeObject : testersOverTimeObjectList) {
                    Integer year = Integer.parseInt(testersOverTimeObject[0].toString());
                    Integer month = Integer.parseInt(testersOverTimeObject[1].toString());
                    Integer numberOf = Integer.parseInt(testersOverTimeObject[2].toString());
                    yearArray.put(year);
                    monthArray.put(month);
                    numberOfArray.put(numberOf);
                }
                testersOverTime.put("year", yearArray);
                testersOverTime.put("month", monthArray);
                testersOverTime.put("numberOf", numberOfArray);
                
                em.close();
                
                System.out.println("Userek játékai lekérdezve!");
                return testersOverTime;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllUser hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }
    
}
        
