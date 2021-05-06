package Service;

import Modell.Statistics;
import Repository.StatisticsRepo;
import java.util.List;

public class StatisticsService {

    public static List<Object[]> getAllStatisticsByUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("getAllStatisticsByUser");
        return StatisticsRepo.getAllStatisticsByUser(id);
    }

    public static Boolean addStatistics(Statistics statistics) {
        System.out.println("------------------------");
        System.out.println("addStatistics");
        return StatisticsRepo.addStatistics(statistics);
    }
}
