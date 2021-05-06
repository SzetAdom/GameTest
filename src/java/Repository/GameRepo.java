/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Modell.Game;
import Modell.Genre;
import Modell.Database;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class GameRepo {

    public static Boolean createGame(Game game) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("gameCreate");

                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_description", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_dev", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_release", Date.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_price", Integer.class, ParameterMode.IN);

                spq.setParameter("in_name", game.getName());
                spq.setParameter("in_description", game.getDescriptionOfGame());
                spq.setParameter("in_dev", game.getDev());
                spq.setParameter("in_release", game.getReleaseDate());
                spq.setParameter("in_price", game.getPrice());

                spq.execute();

                em.close();
                
                System.out.println("Game sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("createGame Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<String> getGame(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("gameGet");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.setParameter("in_id", id);

                List<Object[]> gameObjectList = spq.getResultList();
                Game game = null;
                ArrayList<String> list = new ArrayList<>();
                for (Object[] gameObject : gameObjectList) {
                    Integer gameId = Integer.parseInt(gameObject[0].toString());
                    String name = gameObject[1].toString();
                    String description = gameObject[2].toString();
                    String dev = gameObject[3].toString();
                    java.sql.Date releaseDate = java.sql.Date.valueOf(gameObject[4].toString());
                    Integer price = Integer.parseInt(gameObject[5].toString());
                    Genre genre = new Genre(0);
                    list.add(gameId.toString());
                    list.add(name);
                    list.add(description);
                    list.add(dev);
                    list.add(gameObject[4].toString());
                    list.add(price.toString());
                    list.add(genre.toString());
                    if (gameObject[6] != null) {
                        genre = new Genre(gameObject[6].toString());
                    }
                    game = new Game(gameId, name, description, dev, releaseDate, price, genre);
                }

                em.close();
                
                System.out.println("Game lekérdezve!");
                return list;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("getGame hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static List<Game> getAllGame() {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("gameList");

                List<Object[]> gameObjectList = spq.getResultList();
                List<Game> gameList = new ArrayList<>();

                for (Object[] gameObject : gameObjectList) {
                    Integer gameId = Integer.parseInt(gameObject[0].toString());
                    String name = gameObject[1].toString();
                    String description = gameObject[2].toString();
                    String dev = gameObject[3].toString();
                    java.sql.Date releaseDate = java.sql.Date.valueOf(gameObject[4].toString());
                    Integer price = Integer.parseInt(gameObject[5].toString());
                    Genre genre = new Genre(0);
                    if (gameObject[6] != null) {
                        genre = new Genre(gameObject[6].toString());
                    }
                    Game game = new Game(gameId, name, description, dev, releaseDate, price, genre);
                    gameList.add(game);
                }
                em.close();
                
                System.out.println("Gamek lekérdezve!");
                return gameList;
            } catch (Exception ex) {
                em.close();
                
                System.out.println("gameList hiba! - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Boolean setGameActive(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("gameSetActive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();
                em.close();
                
                System.out.println("Game aktiv sikeres!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("gameSetActive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean gameSetInactive(Integer id) {
        try {
            
            EntityManager em = Database.getDbConn();

            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("gameSetInactive");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();
                
                System.out.println("Game inaktiv sikeres!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("gameSetInactive Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }

    }

    public static Boolean updateGame(Game game) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("gameUpdate");
                
                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_description", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_dev", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_release", Date.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_price", Integer.class, ParameterMode.IN);
                
                spq.setParameter("in_id", game.getGameId());
                spq.setParameter("in_name", game.getName());
                spq.setParameter("in_description", game.getDescriptionOfGame());
                spq.setParameter("in_dev", game.getDev());
                spq.setParameter("in_release", game.getReleaseDate());
                spq.setParameter("in_price", game.getPrice());

                spq.execute();

                em.close();
                
                System.out.println("Game sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();
                
                System.out.println("updateGame Hiba! - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }
}
