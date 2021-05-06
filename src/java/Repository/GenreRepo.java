package Repository;

import Modell.Database;
import Modell.Genre;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class GenreRepo {

    public static Boolean addGenre(String name) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genreCreate");

                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);

                spq.setParameter("in_name", name);

                spq.execute();

                em.close();
                
                System.out.println("Genre sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("addGenre Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static List<Genre> getAllGenre() {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genreList");

                List<Object[]> genreObjectList = spq.getResultList();
                List<Genre> genreList = new ArrayList();
                for (Object[] genreObject : genreObjectList) {
                    Integer id = Integer.parseInt(genreObject[0].toString());
                    String name = genreObject[1].toString();

                    Genre genre = new Genre(id, name);
                    genreList.add(genre);
                }

                em.close();
                
                System.out.println("Genre-k lekérdezve!");
                return genreList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllGenre hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Boolean setGenreActive(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genreSetActive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Genre aktiv sikeres!");
                return true;

            } catch (Exception ex) {
                System.out.println("setGenreActive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean setGenreInactive(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genreSetInactive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Genre inaktiv sikeres!");
                return true;

            } catch (Exception ex) {
                System.out.println("setGenreInactive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean updateGenre(Genre gender) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genreUpdate");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);

                spq.setParameter("in_id", gender.getGenreId());
                spq.setParameter("in_name", gender.getDescriptionOfGenre());

                spq.execute();

                em.close();
                
                System.out.println("Genre sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                System.out.println("updateGenre Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

}
