package Service;

import Repository.AchievementUserRepo;

public class AchievementUserService {

    public static Boolean linkAchievementUser(Integer userId, Integer achiId) {
        System.out.println("------------------------");
        System.out.println("linkAchievementUser");
        return AchievementUserRepo.linkAchievementUser(userId, achiId);
    }
}
