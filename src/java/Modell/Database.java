package Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

    public static EntityManager getDbConn() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            return emf.createEntityManager();
        } catch (Exception e) {
            System.out.println("EM ERROR - " + e.getMessage() + e.getLocalizedMessage());
        }
        return null;
    }
}
