package Repository;

import Modell.AchievementType;
import Modell.Database;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class AchievementTypeRepo {

    public static boolean addAchievementType(String name) {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementTypeCreate");

                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);

                spq.setParameter("in_name", name);

                spq.execute();

                em.close();
                
                System.out.println("Achievement sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {

                em.close();
                
                System.out.println("addAchievementType Hiba! - " + ex.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static List<AchievementType> getAllAchievementType() {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("achievementTypeList");

                List<Object[]> achievementTypeObjectList = spq.getResultList();
                List<AchievementType> achievementList = new ArrayList<>();

                for (Object[] achievementTypeObject : achievementTypeObjectList) {
                    Integer achiTypeId = Integer.parseInt(achievementTypeObject[0].toString());
                    String name = achievementTypeObject[1].toString();
                    AchievementType achievementType = new AchievementType(achiTypeId, name);
                    achievementList.add(achievementType);
                }

                spq.execute();
                em.close();
                
                System.out.println("Achievementek sikeresen lekérve!");
                return achievementList;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllAchievementType Hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }

    }
}
