package praktikum;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class BunTest {

    private Bun bun;

    private String name;
    private float price;

    public static final double DELTA = 0.0;

    @Before
    public void createRandomBun() {
        name = new Faker(new Locale("en"))
                .space()
                .planet();

        price = new Faker(new Locale("ru"))
                .number()
                .randomDigit();

        bun = new Bun(name, price);
    }

    @Test
    public void getNameReturnsValidName() {
        String expectedName = name;
        String actualName = bun.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void getPriceReturnsValidPrice() {
        float expectedPrice = price;
        float actualPrice = bun.getPrice();

        assertEquals(expectedPrice, actualPrice, DELTA);
    }
}