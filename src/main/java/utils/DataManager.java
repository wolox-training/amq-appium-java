package utils;

import dtos.MovieData;

public class DataManager {

    private DataManager(){}

    private static final ThreadLocal<MovieData> movieInformation = ThreadLocal.withInitial(MovieData::new);

    public static MovieData getMovieInformation() {
        return movieInformation.get();
    }

    public static void remove() {
        movieInformation.remove();
    }
}
