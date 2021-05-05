package Service;

import Modell.Achievement;
import Repository.AchievementRepo;
import java.util.List;

public class AchievementService {

    public static boolean createAchievement(Achievement achievement) {
        System.out.println("------------------------");
        System.out.println("createAchievement");
        return AchievementRepo.createAchievement(achievement);
    }

    public static Achievement getAchievement(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAchievement");
        return AchievementRepo.getAchievement(id);
    }

    public static List<Achievement> getAllAchievement() {
        System.out.println("------------------------");
        System.out.println("getAllAchievement");
        return AchievementRepo.getAllAchievement();
    }

    public static List<Achievement> getAllAchievementByGame(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllAchievementByGame");
        return AchievementRepo.getAllAchievementByGame(id);
    }

    public static List<Achievement> getAllAchievementByUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllAchievementByUser");
        return AchievementRepo.getAllAchievementByUser(id);
    }

    public static List<Achievement> getAllAchievementByUser_Game(Integer id, Integer gameId) {
        System.out.println("------------------------");
        System.out.println("getAllAchievementByUser_Game");
        return AchievementRepo.getAllAchievementByUserGame(id, gameId);
    }

    public static boolean setAchievementActive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setAchievemenActive");
        return AchievementRepo.setAchievementActive(id);
    }

    public static boolean setAchievementInactive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setAchievemenInactive");
        return AchievementRepo.setAchievementInactive(id);
    }

    public static boolean updateAchievement(Achievement achievement) {
        System.out.println("------------------------");
        System.out.println("updateAchievement");
        return AchievementRepo.updateAchievement(achievement);
    }
}
