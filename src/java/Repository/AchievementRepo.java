package Repository;

import Modell.Achievement;
import Modell.AchievementType;
import Modell.Database;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class AchievementRepo {

    public static boolean createAchievement(Achievement achievement) {
        try {
            
            EntityManager em = Database.getDbConn();

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
                
                System.out.println("Achievement sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                
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
            
            EntityManager em = Database.getDbConn();

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
//                    Integer prerequisite = Integer.parseInt(achievementObject[4].toString()); --elméletileg a prerequisite

                    achievement = new Achievement(achiId, gameId, description, 0, achievementType);
                }

                em.close();
                
                System.out.println("Achievement lekérdezve!");
                return achievement;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAchievement hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }

    }

    public static List<Achievement> getAllAchievement() {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementList");

                List<Object[]> achievementObjectList = spq.getResultList();
                List<Achievement> achievementList = new ArrayList<>();
                for (Object[] achievementObject : achievementObjectList) {
                    Integer achiId = Integer.parseInt(achievementObject[0].toString());
                    Integer gameId = Integer.parseInt(achievementObject[1].toString());
                    String description = achievementObject[2].toString();
                    AchievementType achievementType = new AchievementType(achievementObject[3].toString());
//                    Integer prerequisite = Integer.parseInt(achievementObject[4].toString()); --elméletileg a prerequisite
                    Achievement achievement = new Achievement(achiId, gameId, description, 0, achievementType);

                    achievementList.add(achievement);
                }

                em.close();
                
                System.out.println("Achievementek lekérdezve!");
                return achievementList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllAchievement hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static List<Achievement> getAllAchievementByGame(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementListbyGame");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                List<Object[]> achievementObjectList = spq.getResultList();
                List<Achievement> achievementList = new ArrayList<>();
                for (Object[] achievementObject : achievementObjectList) {
                    Integer achiId = Integer.parseInt(achievementObject[0].toString());
                    Integer gameId = Integer.parseInt(achievementObject[1].toString());
                    String description = achievementObject[2].toString();
                    AchievementType achievementType = new AchievementType(achievementObject[3].toString());
                    Achievement achievement = new Achievement(achiId, gameId, description, 0, achievementType);

                    achievementList.add(achievement);
                }

                em.close();
                
                System.out.println("Achievementek by game lekérdezve!");
                return achievementList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllAchievementByGame hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static List<Achievement> getAllAchievementByUser(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementListbyUser");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                List<Object[]> achievementObjectList = spq.getResultList();
                List<Achievement> achievementList = new ArrayList<>();
                for (Object[] achievementObject : achievementObjectList) {
                    Integer achiId = Integer.parseInt(achievementObject[0].toString());
                    Integer gameId = Integer.parseInt(achievementObject[1].toString());
                    String description = achievementObject[2].toString();
                    AchievementType achievementType = new AchievementType(achievementObject[3].toString());
                    Achievement achievement = new Achievement(achiId, gameId, description, 0, achievementType);

                    achievementList.add(achievement);
                }

                em.close();
                
                System.out.println("Achievementek by user lekérdezve!");
                return achievementList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllAchievementByUser hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static List<Achievement> getAllAchievementByUserGame(Integer id, Integer pGameId) {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementListbyUser_Game");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_game_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);
                spq.setParameter("in_game_id", pGameId);

                List<Object[]> achievementObjectList = spq.getResultList();
                List<Achievement> achievementList = new ArrayList<>();
                for (Object[] achievementObject : achievementObjectList) {
                    Integer achiId = Integer.parseInt(achievementObject[0].toString());
                    Integer gameId = Integer.parseInt(achievementObject[1].toString());
                    String description = achievementObject[2].toString();
                    AchievementType achievementType = new AchievementType(achievementObject[3].toString());
                    Achievement achievement = new Achievement(achiId, gameId, description, 0, achievementType);

                    achievementList.add(achievement);
                }

                em.close();
                
                System.out.println("Achievementek by user by game lekérdezve!");
                return achievementList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllAchievementByUserGame hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static boolean setAchievementActive(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementSetActive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Achievement aktiv!");
                return true;

            } catch (Exception ex) {
                em.close();
                
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
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementSetInactive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Achievement inaktiv!");
                return true;

            } catch (Exception ex) {
                em.close();
                
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
            
            EntityManager em = Database.getDbConn();
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
                
                System.out.println("Achievement sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("updateAchievement Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }
}
