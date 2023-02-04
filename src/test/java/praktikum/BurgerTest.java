package praktikum;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class BurgerTest {
    private Database database;
    private Burger burger;

//    private List<Bun> buns;
//    private List<Ingredient> ingredients;

    @Before
    public void setUP() {
//        database = new Database();
//        burger = new Burger();

//        buns = database.availableBuns();
//        ingredients = database.availableIngredients();
//
//        burger.setBuns(buns.get(0));
//
//        burger.addIngredient(ingredients.get(1));
//        burger.addIngredient(ingredients.get(2));
    }

    @Test
    public void setBunsReturnsNewBuns() {
        initFieldBun();

        assertNotNull(burger.bun);
    }

    @Test
    public void addIngredient() {
        initFieldIngredient();

        assertNotNull(burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient() {
        initFieldIngredient();
        removeIngredients();

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient() {
        initFieldIngredient();
        List<Ingredient> expectedIngredients = getExpectedIngredients();

        burger.moveIngredient(1, 0);
        List<Ingredient> actualIngredients = Collections.singletonList(burger.ingredients.get(0));

        assertNotEquals(expectedIngredients.get(0), actualIngredients.get(0));
    }

    private void initFieldIngredient() {
        burger = new Burger();
        burger.addIngredient(new Ingredient(SAUCE, getRandomName(), getRandomPrice()));
        burger.addIngredient(new Ingredient(FILLING, getRandomName(), getRandomPrice()));
    }

    private void removeIngredients() {
        burger.removeIngredient(0);
        burger.removeIngredient(0);
    }

    private List<Ingredient> getExpectedIngredients() {
        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(burger.ingredients.get(0));
        return expectedIngredients;
    }

    private void initFieldBun() {
        burger = new Burger();
        burger.setBuns(new Bun(getRandomName(), getRandomPrice()));
    }

    private String getRandomName() {
        return new Faker(new Locale("en"))
                .space()
                .planet();
    }

    private float getRandomPrice() {
        return (float) new Faker()
                .number()
                .randomDigit();
    }
}