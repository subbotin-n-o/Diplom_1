package praktikum;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

//    private Database database;
    private Burger burger;

    public static final double DELTA = 0.0;

//    private List<Bun> buns;
//    private List<Ingredient> ingredients;

    @Before
    public void createBurger() {
        burger = new Burger();
    }

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

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
        List<Ingredient> expectedIngredients = getIngredient();

        burger.moveIngredient(1, 0);
        List<Ingredient> actualIngredients = Collections.singletonList(burger.ingredients.get(0));

        assertNotEquals(expectedIngredients.get(0), actualIngredients.get(0));
    }

    @Test
    public void getPriceReturnsValidPrice() {
        initFieldIngredient(new Ingredient(FILLING, getRandomName(), 2.0F),new Ingredient(SAUCE, getRandomName(), 2.0F));
        initFieldBun(new Bun(getRandomName(), 1.0F));

        float expectedPrice = 6.0F;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, DELTA);
    }

    private void initFieldIngredient(Ingredient ingredient1, Ingredient ingredient2) {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    private void initFieldBun(Bun bun) {
        burger.setBuns(bun);
    }

    private void initFieldIngredient() {
        burger.addIngredient(new Ingredient(SAUCE, getRandomName(), getRandomPrice()));
        burger.addIngredient(new Ingredient(FILLING, getRandomName(), getRandomPrice()));
    }

    private void initFieldBun() {
        burger.setBuns(new Bun(getRandomName(), getRandomPrice()));
    }

    private void removeIngredients() {
        burger.removeIngredient(0);
        burger.removeIngredient(0);
    }

    private List<Ingredient> getIngredient() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(burger.ingredients.get(0));
        return ingredients;
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