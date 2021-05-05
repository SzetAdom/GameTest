/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Modell.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReviewRepo {
    public static JSONObject getScoreDistribution() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("reviewScoreDistribution");

                List<Object[]> scoreDistributionObjectList = spq.getResultList();
                JSONObject scoreDistribution = new JSONObject();
                JSONArray scoreArray = new JSONArray();
                JSONArray numberOfArray = new JSONArray();
                for (Object[] scoreDistributionObject : scoreDistributionObjectList) {
                    Integer score = Integer.parseInt(scoreDistributionObject[0].toString());
                    Integer numberOf = Integer.parseInt(scoreDistributionObject[1].toString());
                    scoreArray.put(score);
                    numberOfArray.put(numberOf);
                }
                scoreDistribution.put("score", scoreArray);
                scoreDistribution.put("numberOf", numberOfArray);
                em.close();
                emf.close();
                System.out.println("Review score eloszlás lekérdezve!");
                return scoreDistribution;
            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("getAllUser hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }
    public static JSONObject getReviewsOverTime() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("reviewsOverTime");

                List<Object[]> reviewsOverTimeObjectList = spq.getResultList();
                JSONObject reviewsOverTime = new JSONObject();
                JSONArray yearArray = new JSONArray();
                JSONArray monthArray = new JSONArray();
                JSONArray numberOfArray = new JSONArray();
                for (Object[] reviewsOverTimeObject : reviewsOverTimeObjectList) {
                    Integer year = Integer.parseInt(reviewsOverTimeObject[0].toString());
                    Integer month = Integer.parseInt(reviewsOverTimeObject[1].toString());
                    Integer numberOf = Integer.parseInt(reviewsOverTimeObject[2].toString());
                    yearArray.put(year);
                    monthArray.put(month);
                    numberOfArray.put(numberOf);
                }
                reviewsOverTime.put("year", yearArray);
                reviewsOverTime.put("month", monthArray);
                reviewsOverTime.put("numberOf", numberOfArray);

                em.close();
                emf.close();
                System.out.println("Review over time lekérdezve!");
                return reviewsOverTime;
            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("getAllUser hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }
}
