package hw3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieDatabase {

    private ArrayList<Movie> movieList;
    private ArrayList<Actor> actorList;

    public MovieDatabase() {
        this.movieList = new ArrayList<Movie>();
        this.actorList = new ArrayList<Actor>();
    }

    public ArrayList<Movie> getMovieList() { return movieList;}

    public ArrayList<Actor> getActorList() { return actorList;}

    public void addMovie(String name, String[] actors) {
        // Create movie
        Movie newMovie = new Movie(name, new ArrayList<Actor>(), 0);

        // If movie not in movieList, add it
        boolean exists = false;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName() == name) {
                exists = true;
            }
        }
        // Only run code if movie doesnt already exist
        if (!exists) {
            // Loop through actors, make them Actor classes then link to movie
            for (int i = 0; i < actors.length; i++) {
                // Check if actor already exists
                boolean actorExists = false;
                int matchingId = 0;
                for (int j = 0; j < actorList.size(); j++) {
                    if (actorList.get(j).getName() == actors[i]) {
                        actorExists = true;
                        matchingId = i;
                    }
                }
                // Add actor if doesnt exist
                if (actorExists) {
                    // Find matchingActor and link to newMovie
                    Actor matchingActor = actorList.get(matchingId);
                    ArrayList<Movie> newMovieList = matchingActor.getMovies();
                    newMovieList.add(newMovie);
                    matchingActor.setMovies(newMovieList);

                    // Add this matchingActor to the newMovie
                    ArrayList<Actor> newMovieActors = new ArrayList<Actor>();
                    newMovieActors.add(matchingActor);
                    newMovie.setActors(newMovieActors);
                }
                else {
                    // Create newActor and add to actorList
                    ArrayList<Movie> newMovieArray = new ArrayList<Movie>();
                    newMovieArray.add(newMovie);
                    Actor newActor = new Actor();
                    newActor.setName(actors[i]);
                    newActor.setMovies(newMovieArray);
                    actorList.add(newActor);

                    // Add newMovie to the newActor
                    ArrayList<Movie> newMovieList = new ArrayList<Movie>();
                    newMovieList.add(newMovie);
                    newActor.setMovies(newMovieList);

                    // Add this newActor to the newMovie
                    ArrayList<Actor> newMovieActors = new ArrayList<Actor>();
                    newMovieActors.add(newActor);
                    newMovie.setActors(newMovieActors);

                }
            }
            movieList.add(newMovie);
        }
    }

    public void addRating(String name, double rating) {
        // Add a rating to a movie
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().equals(name)) {
                movieList.get(i).setRating(rating);
            }
        }
    }

    public void updateRating(String name, double newRating) {
        // Update the rating of a movie
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().equals(name)) {
                movieList.get(i).setRating(newRating);
            }
        }
    }

    public String getBestActor() {
        // Get best actor based on average movie rating

        Actor bestActor = actorList.get(0);
        double bestAvg = 0;

        for (int i = 1; i < actorList.size(); i++) {
            // Get bestActor avg
            double bestTotal = 0;
            double bestCount = 0;

            for (int m = 0; m < bestActor.getMovies().size(); m++) {
                bestTotal += bestActor.getMovies().get(m).getRating();
                bestCount += 1;
            }
            bestAvg = bestTotal/bestCount;

            // Get nextActor avg
            Actor nextActor = actorList.get(i);
            double nextTotal = 0;
            double nextCount = 0;

            for (int m = 0; m < nextActor.getMovies().size(); m++) {
                nextTotal += nextActor.getMovies().get(m).getRating();
                nextCount += 1;
            }
            double nextAvg = nextTotal/nextCount;

            if (nextAvg > bestAvg) {
                bestActor = nextActor;
            }
        }

        return bestActor.getName();
    }

    public String getBestMovie() {
        // Get best movie based on average movie rating

        Movie bestMovie = movieList.get(0);

        for (int i = 1; i < movieList.size(); i++) {
            Movie nextMovie =  movieList.get(i);
            if (nextMovie.getRating() > bestMovie.getRating()) {
                bestMovie = nextMovie;
            }
        }

        return bestMovie.getName();
    }

    public static void main(String[] args) {
        //Create a new instance of a movieDatabase
        MovieDatabase mdb = new MovieDatabase();
        // Loop through lines in movies.txt
        try {
            File myObj = new File("U:/Desktop/penngineering/movies/src/movies/movies.txt");
            Scanner myReader = new Scanner(myObj);

            // Lists to parse into
            ArrayList<String> parsedMovies = new ArrayList<String>();
            ArrayList<ArrayList<String>> parsedActors = new ArrayList<ArrayList<String>>();

            while (myReader.hasNextLine()) {
                // Reformat csv
                String data = myReader.nextLine();
                String[] arrData = data.split(", ");
                String actorName = arrData[0];
                String[] actorFilms = Arrays.copyOfRange(arrData, 1, arrData.length);

                // Parse from actor: movies[] to movie: actors[]
                for (int i = 0; i < actorFilms.length; i++) {
                    // If movie not in list, add it at the end
                    // Also add a corresponding empty actors list
                    if (!(parsedMovies.contains(actorFilms[i]))) {
                        // Add movie to end of list
                        parsedMovies.add(actorFilms[i]);
                        parsedActors.add(new ArrayList<String>());
                    }
                    // Get index of movie
                    int idx = parsedMovies.indexOf(actorFilms[i]);
                    // Add actor at the corresponding index in parsedActors
                    parsedActors.get(idx).add(actorName);
                }
            }
            myReader.close();

            // Run addMovie on parsed data
            // First converting parsedActors to a String[]
            for (int i = 0; i < parsedMovies.size(); i++) {
                // Convert ArrayList of actors to String[] of actors
                String[] arrActors = new String[parsedActors.get(i).size()];
                for (int j = 0; j < parsedActors.get(i).size(); j++) {
                    arrActors[j] = parsedActors.get(i).get(j);
                }
                // Run addMovie on: String movie, String[] actors
                mdb.addMovie(parsedMovies.get(i), arrActors);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // read the ratings file and add the ratings to the movies

        try {
            File myObj = new File("U:/Desktop/penngineering/movies/src/movies/ratings.txt");
            Scanner myReader = new Scanner(myObj);

            // Lists to parse into
            ArrayList<String> parsedMovies = new ArrayList<String>();
            ArrayList<ArrayList<String>> parsedActors = new ArrayList<ArrayList<String>>();

            // Read first line to get out the way
            myReader.nextLine();

            while (myReader.hasNextLine()) {
                // Reformat csv
                String data = myReader.nextLine();
                String[] arrData = data.split("\t");

                // Split out data
                String movieName = arrData[0];
                double movieRating = Double.parseDouble(arrData[1]);

                // Add rating for movie
                mdb.addRating(movieName, movieRating);

            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Print best movie
        System.out.println(mdb.getBestMovie());

        // Print best actor
        System.out.println(mdb.getBestActor());




    }
}
