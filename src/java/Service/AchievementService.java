package Service;

import Modell.Achievement;
import Repository.AchievementRepo;

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

    public static boolean getAllAchievement(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllAchievement");
        return AchievementRepo.getAllAchievement(id);
    }

    public static boolean getAllAchievementByGame(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllAchievementByGame");
        return AchievementRepo.getAllAchievementByGame(id);
    }

    public static boolean getAllAchievementByUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllAchievementByUser");
        return AchievementRepo.getAllAchievementByUser(id);
    }

    public static boolean getAllAchievementByUser_Game(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllAchievementByUser_Game");
        return AchievementRepo.getAllAchievementByUser_Game(id);
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
