/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Repository.ReviewRepo;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Win10
 */
public class ReviewService {
    
    public static JSONObject getScoreDistribution() {
        System.out.println("------------------------");
        System.out.println("getScoreDistribution");
        return ReviewRepo.getScoreDistribution();
    }
    public static JSONObject getReviewsOverTime() {
        System.out.println("------------------------");
        System.out.println("getReviewsOverTime");
        return ReviewRepo.getReviewsOverTime();
    }
    public static JSONArray getReviewListbyUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getReviewsOverTime");
        return ReviewRepo.getReviewListbyUser(id);
    }
    public static JSONArray getReviewListbyGame(Integer id) {
        System.out.println("------------------------");
        System.out.println("reviewListbyGame");
        return ReviewRepo.getReviewListbyGame(id);
    }
    
}
