package Service;

import Modell.Genre;
import Repository.GenreRepo;
import java.util.List;

public class GenreService {

    public static Boolean addGenre(String name) {
        System.out.println("------------------------");
        System.out.println("addGenre");
        return GenreRepo.addGenre(name);
    }

    public static List<Genre> getAllGenre() {
        System.out.println("------------------------");
        System.out.println("getAllGenre");
        return GenreRepo.getAllGenre();
    }

    public static Boolean setGenreActive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setGenreActive");
        return GenreRepo.setGenreActive(id);
    }

    public static Boolean setGenreInactive(Integer id) {
        System.out.println("------------------------");
        System.out.println("setGenreInactive");
        return GenreRepo.setGenreInactive(id);
    }

    public static Boolean updateGenre(Genre genre) {
        System.out.println("------------------------");
        System.out.println("updateGenre");
        return GenreRepo.updateGenre(genre);
    }

}
