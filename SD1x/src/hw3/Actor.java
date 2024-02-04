package hw3;

import java.util.ArrayList;

public class Actor {
    private String name;
    private ArrayList<Movie> movies;

    public Actor() {
        this.movies = null;
        this.name = null;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public boolean setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        return true;
    }
}
