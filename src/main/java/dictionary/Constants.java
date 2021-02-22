package dictionary;

import java.util.Arrays;
import java.util.List;

public class Constants {

    private Constants() {
    }

    public static final String LOGIN_BUTTON = "Login";
    public static final String SIGN_UP_BUTTON = "Sign Up";
    public static final String ERROR_INPUTS_LOGIN = "Invalid username and/or password";
    public static final String TOPIC_DAILY = "Trending Daily";
    public static final String TOPIC_WEEKLY = "Trending Weekly";
    public static final String TOPIC_POPULAR = "Popular";
    public static final String TOPIC_TOP_RATED = "Top Rated";
    public static final List<String> TOPICS = Arrays.asList("Trending_Daily_section", "Trending_Weekly_section", "Popular_section", "Top_Rated_section");

}
