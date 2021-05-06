/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Modell.Game;
import Repository.GameRepo;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    public static Boolean gameCreate(Game game) {
        System.out.println("------------------------");
        System.out.println("gameCreate");
        return GameRepo.createGame(game);
    }

    public static ArrayList<String> getGame(Integer id) {
        System.out.println("------------------------");
        System.out.println("gameGet");
        return GameRepo.getGame(id);
    }

    public static List<Game> getAllGame() {
        System.out.println("------------------------");
        System.out.println("getAllGame");
        return GameRepo.getAllGame();
    }

    public static Boolean setGameActive(Integer id) {
        System.out.println("------------------------");
        System.out.println("gameSetActive");
        return GameRepo.setGameActive(id);
    }

    public static Boolean setGamInactive(Integer id) {
        System.out.println("------------------------");
        System.out.println("gameSetInactive");
        return GameRepo.gameSetInactive(id);
    }

    public static Boolean updateGame(Game game) {
        System.out.println("------------------------");
        System.out.println("gameUpdate");
        return GameRepo.updateGame(game);
    }

}
