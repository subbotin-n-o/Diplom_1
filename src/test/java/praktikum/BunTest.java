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

    @Before
    public void createBun() {
        Faker faker = new Faker(new Locale("ru"));
        name = faker.name().firstName();
        price = faker.number().randomDigit();

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

        assertEquals(expectedPrice, actualPrice, 0.0);
    }
}