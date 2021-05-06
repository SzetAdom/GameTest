package Repository;

import Modell.Database;
import Modell.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenderRepo {

    public static Boolean createGender(String nameOfGender) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genderCreate");

                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);

                spq.setParameter("in_name", nameOfGender);

                spq.execute();

                em.close();
                
                System.out.println("Gender sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("nameOfGender Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean updateGender(Gender gender) {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genderUpdate");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);

                spq.setParameter("in_id", gender.getGenderId());
                spq.setParameter("in_name", gender.getNameOfGender());
                spq.execute();

                em.close();
                
                System.out.println("Gender sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("genderUpdate Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static List<Gender> getAllGender() {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genderList");
                List<Object[]> genderObjectList = spq.getResultList();
                List<Gender> genderList = new ArrayList();
                for (Object[] genderObject : genderObjectList) {
                    Integer id = Integer.parseInt(genderObject[0].toString());
                    String name = genderObject[1].toString();

                    Gender gender = new Gender(id, name);
                    genderList.add(gender);
                }
                em.close();
                
                System.out.println("Genderek lekérdezve!");
                return genderList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllGender hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Boolean setGenderActive(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genderSetActive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Gender aktiv sikeres!");
                return true;

            } catch (Exception ex) {

                em.close();
                
                System.out.println("setGenderActive Hiba! - " + ex.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean setGenderInactive(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genderSetInactive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Gender inaktiv sikeres!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("setGenderActive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }
    public static JSONObject getGenderDistribution() {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("genderDistribution");

                List<Object[]> genderDistributionObjectList = spq.getResultList();
                JSONObject genderDistribution = new JSONObject();
                JSONArray genderArray = new JSONArray();
                JSONArray numberOfArray = new JSONArray();
                for (Object[] genderDistributionObject : genderDistributionObjectList) {
                    String gender = genderDistributionObject[0].toString();
                    Integer numberOf = Integer.parseInt(genderDistributionObject[1].toString());
                    genderArray.put(gender);
                    numberOfArray.put(numberOf);
                }
                genderDistribution.put("gender", genderArray);
                genderDistribution.put("numberOf", numberOfArray);
                em.close();
                
                System.out.println("Review score eloszlás lekérdezve!");
                return genderDistribution;
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
