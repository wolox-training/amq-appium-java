package utils;


import com.github.javafaker.Faker;

/**
 * The type Provider base.
 */
public class ProviderBase {

    /**
     * The Faker.
     */
    protected Faker faker;

    /**
     * Instantiates a new Provider base.
     */
    public ProviderBase() {
        faker = new Faker();
    }


}
