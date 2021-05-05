package Service;

import Modell.Statistics;
import Repository.StatisticsRepo;
import java.util.List;

public class StatisticsService {

    public static List<Statistics> getAllStatisticsByUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllStatisticsByUser");
        return StatisticsRepo.getAllStatisticsByUser(id);
    }
}
