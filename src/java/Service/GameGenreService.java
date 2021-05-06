package Service;

import Repository.GameGenreRepo;

public class GameGenreService {

    public static Boolean linkGameGenre(Integer gameId, Integer genreId) {
        System.out.println("------------------------");
        System.out.println("linkGameGenre");
        return GameGenreRepo.linkGameGenre(gameId, genreId);
    }

}
