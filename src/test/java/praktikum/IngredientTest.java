package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.GenerateRandomData.getRandomName;
import static praktikum.GenerateRandomData.getRandomPrice;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {

    private Ingredient ingredient;

    private final IngredientType type;
    private final String name;
    private final float price;

    public static final double DELTA = 0.0;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {SAUCE, getRandomName(), getRandomPrice()},
                {FILLING, getRandomName(), getRandomPrice()},
        };
    }

    @Before
    public void createIngredient() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getPriceReturnsValidPrice() {
        float expectedPrice = price;
        float actualPrice = ingredient.getPrice();

        assertEquals(expectedPrice, actualPrice, DELTA);
    }

    @Test
    public void getNameReturnsValidName() {
        String expectedName = name;
        String actualName = ingredient.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void getTypeReturnsValidType() {
        IngredientType expectedType = type;
        IngredientType actualType = ingredient.getType();

        assertEquals(expectedType, actualType);
    }

}