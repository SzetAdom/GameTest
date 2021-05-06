package Service;

import Modell.AchievementType;
import Repository.AchievementTypeRepo;
import java.util.List;

public class AchievementTypeService {

    public static Boolean addAchievementType(String name) {
        System.out.println("------------------------");
        System.out.println("addAchievementType");
        return AchievementTypeRepo.addAchievementType(name);
    }

    public static List<AchievementType> getAllAchievementType() {
        System.out.println("------------------------");
        System.out.println("getAllAchievementType");
        return AchievementTypeRepo.getAllAchievementType();
    }

}
