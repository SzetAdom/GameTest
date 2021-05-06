package Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
    static EntityManagerFactory emf = null;
    public static EntityManager getDbConn() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("GameTestPU");
        }
        return emf.createEntityManager();
    }
}
