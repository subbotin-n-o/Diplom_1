package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class DatabaseTest {

    private Database database;

    private List<Bun> buns;
    private List<Ingredient> ingredients;

    public static final double DELTA = 0.0;

    @Before
    public void initFields() {
        buns = getListBuns();
        ingredients = getListIngredients();

        database = new Database();
    }

    @Test
    public void availableBunsReturnsValidListBuns() {
        List<Bun> expectedListBuns = buns;
        List<Bun> actualListBuns = database.availableBuns();

        for (int i = 0; i < buns.size(); i++) {
            assertEquals(expectedListBuns.get(i).getName(), actualListBuns.get(i).getName());
            assertEquals(expectedListBuns.get(i).getPrice(), actualListBuns.get(i).getPrice(), DELTA);
        }
    }

    @Test
    public void availableIngredientsReturnsValidListIngredients() {
        List<Ingredient> expectedListIngredients = ingredients;
        List<Ingredient> actualListIngredients = database.availableIngredients();

        for (int i = 0; i < expectedListIngredients.size(); i++) {
            assertEquals(expectedListIngredients.get(i).getType(), actualListIngredients.get(i).getType());
            assertEquals(expectedListIngredients.get(i).getName(), actualListIngredients.get(i).getName());
            assertEquals(expectedListIngredients.get(i).getPrice(), actualListIngredients.get(i).getPrice(), DELTA);
        }
    }

    private List<Bun> getListBuns() {
        return Arrays.asList(new Bun("black bun", 100),
                new Bun("white bun", 200),
                new Bun("red bun", 300));
    }

    private List<Ingredient> getListIngredients() {
        return Arrays.asList(new Ingredient(SAUCE, "hot sauce", 100),
                new Ingredient(SAUCE, "sour cream", 200),
                new Ingredient(SAUCE, "chili sauce", 300),
                new Ingredient(FILLING, "cutlet", 100),
                new Ingredient(FILLING, "dinosaur", 200),
                new Ingredient(FILLING, "sausage", 300));
    }
}