package com.costa.luiz.tropicalflix.subscription;

class Recommendation {

    private final String movies;
    private final String genres;
    private final String actors;

    Recommendation() {
        this.movies = "Spider Man 2, Matrix, Luca, Duna I, Duna II, Robocop";
        this.genres = "Action, Drama, Cartoon";
        this.actors = "Denzel Washington, Al Pacino, Robert D. Jr";
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "movies='" + movies + '\'' +
                ", genres='" + genres + '\'' +
                ", actors='" + actors + '\'' +
                '}';
    }
}
