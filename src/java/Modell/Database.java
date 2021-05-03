package Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

    public static EntityManager getDbConn() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
        return emf.createEntityManager();
    }
}
