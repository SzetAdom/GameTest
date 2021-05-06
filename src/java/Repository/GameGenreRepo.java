package Repository;

import Modell.Database;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class GameGenreRepo {

    public static Boolean linkGameGenre(Integer gameId, Integer genreId) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("Link_game_genre");

                spq.registerStoredProcedureParameter("in_game_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_genre_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_game_id", gameId);
                spq.setParameter("in_genre_id", genreId);

                System.out.println(gameId);
                System.out.println(genreId);

                spq.execute();
                em.close();
                
                System.out.println("Link game és genre között sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("linkGameGenre Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

}
