package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static praktikum.GenerateRandomData.getRandomName;
import static praktikum.GenerateRandomData.getRandomPrice;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    public static final double DELTA = 0.0;

    @Before
    public void createBurger() {
        burger = new Burger();
    }

    @Mock
    Database databaseMock;

    @Mock
    Bun bunMock;

    @Mock
    Ingredient ingredientMockOne;

    @Mock
    Ingredient ingredientMockTwo;

    @Test
    public void setBunsReturnsNewBuns() {
        initFieldBun();

        assertNotNull(burger.bun);
    }

    @Test
    public void addIngredientShouldReturnNonEmptyList() {
        initFieldIngredient();

        assertFalse(burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientShouldReturnEmptyList() {
        initFieldIngredient();
        removeIngredients();

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientShouldReplaceElementsList() {
        initFieldIngredient();
        List<Ingredient> expectedIngredients = Collections.singletonList(burger.ingredients.get(0));

        burger.moveIngredient(1, 0);
        List<Ingredient> actualIngredients = Collections.singletonList(burger.ingredients.get(0));

        assertNotEquals(expectedIngredients.get(0), actualIngredients.get(0));
    }

    @Test
    public void getPriceReturnsValidPrice() {
        initFieldIngredient(new Ingredient(FILLING, getRandomName(), 2.0F), new Ingredient(SAUCE, getRandomName(), 2.0F));
        initFieldBun(new Bun(getRandomName(), 1.0F));

        float expectedPrice = 6.0F;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, DELTA);
    }

    @Test
    public void getReceiptReturnsValidReceipt() {
        Mockito.when(databaseMock.availableBuns()).thenReturn(new ArrayList<>(Collections.singletonList(new Bun("black bun", 100))));
        Mockito.when(databaseMock.availableIngredients()).thenReturn(new ArrayList<>(Collections.singletonList(new Ingredient(SAUCE, "hot sauce", 100))));

        List<Bun> buns = databaseMock.availableBuns();
        List<Ingredient> ingredients = databaseMock.availableIngredients();

        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(0));

        String expectedReceipt = String.format("(==== %s ====)%n", "black bun") +
                String.format("= %s %s =%n", "sauce hot", "sauce") +
                String.format("(==== %s ====)%n", "black bun") +
                String.format("%nPrice: %f%n", 300.0F);
        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    private void initFieldIngredient(Ingredient ingredientOne, Ingredient ingredientTwo) {
        ingredientMockOne = ingredientOne;
        ingredientMockTwo = ingredientTwo;

        burger.addIngredient(ingredientMockOne);
        burger.addIngredient(ingredientMockTwo);
    }

    private void initFieldBun(Bun bun) {
        bunMock = bun;
        burger.setBuns(bun);
    }

    private void initFieldIngredient() {
        ingredientMockOne = new Ingredient(SAUCE, getRandomName(), getRandomPrice());
        ingredientMockTwo = new Ingredient(FILLING, getRandomName(), getRandomPrice());

        burger.addIngredient(ingredientMockOne);
        burger.addIngredient(ingredientMockTwo);
    }

    private void initFieldBun() {
        bunMock = new Bun(getRandomName(), getRandomPrice());
        burger.setBuns(bunMock);
    }

    private void removeIngredients() {
        burger.removeIngredient(0);
        burger.removeIngredient(0);
    }

}