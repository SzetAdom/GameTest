package Repository;

import Modell.Database;
import Modell.Game;
import Modell.Statistics;
import Modell.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class StatisticsRepo {

    public static List<Object[]> getAllStatisticsByUser(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("statisticsListbyUser");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                List<Object[]> statisticsObjectList = spq.getResultList();
                List<Statistics> statisticsList = new ArrayList<>();

                /*for (Object[] statisticsObject : statisticsObjectList) {
                    Integer statisticsId = Integer.parseInt(statisticsObject[0].toString());
                    String gameName = statisticsObject[1].toString();
                    String userName = statisticsObject[2].toString();
                    Date firstPlayed = Date.valueOf(statisticsObject[3].toString());
                    Date lastPlayed = Date.valueOf(statisticsObject[4].toString());
                    Integer minutes = Integer.parseInt(statisticsObject[5].toString());

                    System.out.println(statisticsId);
                    System.out.println(gameName);
                    System.out.println(userName);
                    System.out.println(firstPlayed);
                    System.out.println(lastPlayed);
                    System.out.println(minutes);

                    Game game = new Game();
                    game.setName(gameName);

                    User user = new User();
                    user.setUsername(userName);

                    Statistics statistics = new Statistics(statisticsId, game, user, firstPlayed, lastPlayed, minutes);
                    System.out.println(1);

                    statisticsList.add(statistics);
                }*/

                em.close();
                
                System.out.println("Statistics by user lekérdezve!");
                return statisticsObjectList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getAllAchievementByUser hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }
}
