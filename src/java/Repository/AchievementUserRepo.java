package Repository;

import Modell.Database;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class AchievementUserRepo {

    public static Boolean linkAchievementUser(Integer userId, Integer achiId) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("Link_achievement_user");

                spq.registerStoredProcedureParameter("in_user", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_achivement", Integer.class, ParameterMode.IN);

                spq.setParameter("in_user", userId);
                spq.setParameter("in_achivement", achiId);

                spq.execute();
                em.close();
                
                System.out.println("Link user és achi között sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("Link_achievement_user Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }
}
