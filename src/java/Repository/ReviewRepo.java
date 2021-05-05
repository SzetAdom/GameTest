/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Modell.Database;
import Modell.Review;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
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

    public static JSONArray getReviewListbyUser(Integer id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("reviewListbyUser");
                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.setParameter("in_id", id);

                List<Object[]> reviewListbyUserObjectList = spq.getResultList();

                JSONArray reviewListbyUser = new JSONArray();
                for (Object[] reviewListbyUserObject : reviewListbyUserObjectList) {
                    JSONObject review = new JSONObject();

                    Integer reviewId = Integer.parseInt(reviewListbyUserObject[0].toString());
                    Integer userId = Integer.parseInt(reviewListbyUserObject[1].toString());
                    Integer gameId = Integer.parseInt(reviewListbyUserObject[2].toString());
                    String username = reviewListbyUserObject[3].toString();
                    String gameName = reviewListbyUserObject[4].toString();
                    Integer score = Integer.parseInt(reviewListbyUserObject[5].toString());
                    String comment = reviewListbyUserObject[6].toString();
                    String createdId = reviewListbyUserObject[7].toString();

                    review.put("reviewId", reviewId);
                    review.put("userId", userId);
                    review.put("gameId", gameId);
                    review.put("username", username);
                    review.put("gameName", gameName);
                    review.put("score", score);
                    review.put("comment", comment);
                    review.put("createdId", createdId);

                    reviewListbyUser.put(review);
                }

                em.close();
                emf.close();
                System.out.println("Review over time lekérdezve!");
                return reviewListbyUser;
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

    public static JSONArray getReviewListbyGame(Integer id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("reviewListbyGame");
                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.setParameter("in_id", id);

                List<Object[]> reviewListbyGameObjectList = spq.getResultList();

                JSONArray reviewListbyGame = new JSONArray();
                for (Object[] reviewListbyGameObject : reviewListbyGameObjectList) {
                    JSONObject review = new JSONObject();

                    Integer reviewId = Integer.parseInt(reviewListbyGameObject[0].toString());
                    Integer userId = Integer.parseInt(reviewListbyGameObject[1].toString());
                    Integer gameId = Integer.parseInt(reviewListbyGameObject[2].toString());
                    String username = reviewListbyGameObject[3].toString();
                    String gameName = reviewListbyGameObject[4].toString();
                    Integer score = Integer.parseInt(reviewListbyGameObject[5].toString());
                    String comment = reviewListbyGameObject[6].toString();
                    String createdId = reviewListbyGameObject[7].toString();

                    review.put("reviewId", reviewId);
                    review.put("userId", userId);
                    review.put("gameId", gameId);
                    review.put("username", username);
                    review.put("gameName", gameName);
                    review.put("score", score);
                    review.put("comment", comment);
                    review.put("createdId", createdId);

                    reviewListbyGame.put(review);
                }

                em.close();
                emf.close();
                System.out.println("Review over time lekérdezve!");
                return reviewListbyGame;
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

    public static boolean addReview(Review review) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameTestPU");
            EntityManager em = emf.createEntityManager();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("reviewCreate");
                spq.registerStoredProcedureParameter("in_user", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_game", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_score", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_comment", String.class, ParameterMode.IN);

                spq.setParameter("in_user", review.getUserId().getUserId());
                spq.setParameter("in_game", review.getGameId().getGameId());
                spq.setParameter("in_score", review.getScore());
                spq.setParameter("in_comment", review.getComment());

                spq.execute();
                em.close();
                emf.close();
                System.out.println("Review sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                emf.close();
                System.out.println("createReview Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Database connection hiba! - " + ex.getMessage());
            return false;
        }

    }

}
