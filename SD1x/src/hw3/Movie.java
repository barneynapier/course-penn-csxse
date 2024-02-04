package hw3;

import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<Actor> actors;
    private double rating;

    public Movie(String n, ArrayList<Actor> a, double r) {
        this.name = null;
        this.actors = a;
        this.rating = r;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public boolean setActors(ArrayList<Actor> actors) {
        this.actors = actors;
        return true;
    }

    public double getRating() {
        return rating;
    }

    public boolean setRating(double rating) {
        this.rating = rating;
        return true;
    }

}
