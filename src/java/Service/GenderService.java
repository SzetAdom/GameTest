package Service;

import Modell.Gender;
import Repository.GenderRepo;
import java.util.List;
import org.json.JSONObject;

public class GenderService {

    public static Boolean createGender(String nameOfGender) {
        System.out.println("------------------------");
        System.out.println("createGender");
        return GenderRepo.createGender(nameOfGender);
    }

    public static Boolean updateGender(Gender gender) {
        System.out.println("------------------------");
        System.out.println("updateGender");
        return GenderRepo.updateGender(gender);
    }

    public static List<Gender> getAllGender() {
        System.out.println("------------------------");
        System.out.println("getAllGender");
        return GenderRepo.getAllGender();
    }

    public static Boolean setGenderActive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setGenderActive");

        return GenderRepo.setGenderActive(id);
    }

    public static Boolean setGenderInactive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setGenderInactive");

        return GenderRepo.setGenderInactive(id);
    }
    
    public static JSONObject getGenderDistribution() {
        System.out.println("------------------------");
        System.out.println("getGenderDistribution");

        return GenderRepo.getGenderDistribution();
    }
}
