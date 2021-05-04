package Repository;

import Modell.Achievement;
import Modell.AchievementType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class AchievementRepo {

    public static boolean createAchievement(Achievement achievement) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = emf.createEntityManager();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementCreate");
                spq.registerStoredProcedureParameter("in_game_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_descr", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_pre", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_type_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_game_id", achievement.getGameId().getGameId());
                spq.setParameter("in_descr", achievement.getDescriptionOfAchievment());
                spq.setParameter("in_pre", achievement.getPrerequisite().getAchievementId());
                spq.setParameter("in_type_id", achievement.getAchievementType().getAchievementTypeId());

                spq.execute();
                em.close();
                emf.close();
                System.out.println("Achievement sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("createAchievement Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Database connection hiba! - " + ex.getMessage());
            return false;
        }

    }

    public static Achievement getAchievement(Integer id) {
        /*A rekurzív függvény miatt nem működik rendesen
        /*
         */
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = emf.createEntityManager();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementGet");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                List<Object[]> achievementObjectList = spq.getResultList();
                Achievement achievement = null;
                for (Object[] achievementObject : achievementObjectList) {
                    Integer achiId = Integer.parseInt(achievementObject[0].toString());
                    Integer gameId = Integer.parseInt(achievementObject[1].toString());
                    String description = achievementObject[2].toString();
                    AchievementType achievementType = new AchievementType(achievementObject[3].toString());
                    Integer prerequisite = Integer.parseInt(achievementObject[4].toString());

                    achievement = new Achievement(achiId, gameId, description, prerequisite, achievementType);
                }

                em.close();
                emf.close();
                System.out.println("Achievement lekérdezve!");
                return achievement;
            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("getAchievement hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }

    }

    public static boolean getAllAchievement(Integer id) {
        return false;
    }

    public static boolean getAllAchievementByGame(Integer id) {
        return false;
    }

    public static boolean getAllAchievementByUser(Integer id) {
        return false;
    }

    public static boolean getAllAchievementByUser_Game(Integer id) {
        return false;
    }

    public static boolean setAchievementActive(Integer id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = emf.createEntityManager();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementSetActive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                emf.close();
                System.out.println("Achievement aktiv!");
                return true;

            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("setAchievementActive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static boolean setAchievementInactive(Integer id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = emf.createEntityManager();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementSetInactive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                emf.close();
                System.out.println("Achievement inaktiv!");
                return true;

            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("setAchievementInactive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static boolean updateAchievement(Achievement achievement) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = emf.createEntityManager();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementUpdate");

                spq.registerStoredProcedureParameter("ac_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_game_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_descr", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_pre", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_type_id", Integer.class, ParameterMode.IN);

                spq.setParameter("ac_id", achievement.getAchievementId());
                spq.setParameter("in_game_id", achievement.getGameId().getGameId());
                spq.setParameter("in_descr", achievement.getDescriptionOfAchievment());
                spq.setParameter("in_pre", achievement.getPrerequisite().getAchievementId());
                spq.setParameter("in_type_id", achievement.getAchievementType().getAchievementTypeId());

                spq.execute();

                em.close();
                emf.close();
                System.out.println("Achievement sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("updateAchievement Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }
}
