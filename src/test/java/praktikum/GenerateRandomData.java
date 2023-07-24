package praktikum;

import com.github.javafaker.Faker;

import java.util.Locale;

public class GenerateRandomData {
    static String getRandomName() {
        return new Faker(new Locale("en"))
                .space()
                .planet();
    }

    static float getRandomPrice() {
        return (float) new Faker()
                .number()
                .randomDigit();
    }
}