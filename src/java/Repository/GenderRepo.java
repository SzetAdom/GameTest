package Repository;

import Modell.Database;
import Modell.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class GenderRepo {

    public static Boolean createGender(String nameOfGender) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("genderCreate");

            spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);

            spq.setParameter("in_name", nameOfGender);

            spq.execute();
            System.out.println("Gender sikeresen hozzáadva!");
            return true;

        } catch (Exception ex) {
            System.out.println("nameOfGender Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static boolean updateGender(Gender gender) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("genderUpdate");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);

            spq.setParameter("in_id", gender.getGenderId());
            spq.setParameter("in_name", gender.getNameOfGender());
            spq.execute();
            System.out.println("Gender sikeresen frissítve!");
            return true;

        } catch (Exception ex) {
            System.out.println("genderUpdate Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static List<Gender> getAllGender() {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("genderList");

            List<Object[]> genderObjectList = spq.getResultList();
            List<Gender> genderList = new ArrayList();
            for (Object[] genderObject : genderObjectList) {
                Integer id = Integer.parseInt(genderObject[0].toString());
                String name = genderObject[1].toString();

                Gender gender = new Gender(id, name);
                genderList.add(gender);
            }
            System.out.println("Genderek lekérdezve!");
            return genderList;
        } catch (Exception ex) {
            System.out.println("getAllGender hiba! - " + ex.getMessage());
            return null;
        }
    }

    public static boolean setGenderActive(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("genderSetActive");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", id);

            spq.execute();
            System.out.println("Gender aktiv sikeres!");
            return true;

        } catch (Exception ex) {
            System.out.println("setGenderActive Hiba! - " + ex.getMessage());
            return false;
        }
    }

    public static boolean setGenderInactive(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("genderSetInactive");

            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

            spq.setParameter("in_id", id);

            spq.execute();
            System.out.println("Gender inaktiv sikeres!");
            return true;

        } catch (Exception ex) {
            System.out.println("setGenderActive Hiba! - " + ex.getMessage());
            return false;
        }
    }

}
