package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Contains tests for methods in Recipe class
 *
 * @since 0.2.0
 * @author Erlend Sundsdal
 * @version 0.6.2
 */

public class TestRecipe {

  Recipe recipe1;


  @BeforeEach
  void setup() {
    String name = "scrambled Eggs";
    String description = "This recipe shows you how to make scrambled eggs.";
    String procedure = "Break your eggs into a hot frying pan. The scramble them. Voilà";
    Grocery egg = new Grocery("egg", 2, "units");
    Grocery butter = new Grocery("butter", 0.005, "kg");
    int portions = 4;
    ArrayList<Grocery> ingredientList = new ArrayList<>();
    ingredientList.add(egg);
    ingredientList.add(butter);
    recipe1 = new Recipe(name, description, procedure, ingredientList, portions);

  }

  @Test
  void testRecipeConstructor() {
    // Arrange
    String name = "scrambled Eggs";
    String description = "This recipe shows you how to make scrambled eggs.";
    String procedure = "Break your eggs into a hot frying pan. The scramble them. Voilà";
    Grocery egg = new Grocery("egg", 2, "units");
    int portions = 4;

    ArrayList<Grocery> ingredientList = new ArrayList<>();
    ingredientList.add(egg);

    // Act
    Recipe recipe = new Recipe(name, description,procedure, ingredientList, portions);

    // Assert
    assertEquals(name, recipe.getName());
    assertEquals(description, recipe.getDescription());
    assertEquals(procedure, recipe.getProcedure());
    assertEquals(ingredientList, recipe.getIngredientList());
  }


  @Test
  void getNameShouldReturnScrambledEggs() {
    String expectedName = "scrambled Eggs";

    String actualName = recipe1.getName();

    assertEquals(expectedName, actualName);
  }

  @Test
  void getDescriptionShouldReturnCorrectDescription() {
    String expectedDescription = "This recipe shows you how to make scrambled eggs.";

    String actualDescription = recipe1.getDescription();

    assertEquals(expectedDescription, actualDescription);
  }

  @Test
  void getProcedureShouldReturnCorrectProcedure() {
    String expectedProcedure = "Break your eggs into a hot frying pan. The scramble them. Voilà";

    String actualProcedure = recipe1.getProcedure();

    assertEquals(expectedProcedure, actualProcedure);
  }

  @Test
  void getIngredientListShouldReturnCorrectIngredients() {
    ArrayList<Grocery> expectedIngredients = new ArrayList<>();
    expectedIngredients.add(new Grocery("egg", 2, "units"));
    expectedIngredients.add(new Grocery("butter", 0.005, "kg"));

    ArrayList<Grocery> actualIngredients = recipe1.getIngredientList();

    assertEquals(expectedIngredients.get(0).getName(),actualIngredients.get(0).getName());
    assertEquals(expectedIngredients.get(0).getAmount(),actualIngredients.get(0).getAmount());
    assertEquals(expectedIngredients.get(0).getMeasuringUnit(),actualIngredients.get(0).getMeasuringUnit());
    assertEquals(expectedIngredients.get(1).getName(),actualIngredients.get(1).getName());
    assertEquals(expectedIngredients.get(1).getAmount(),actualIngredients.get(1).getAmount());
    assertEquals(expectedIngredients.get(1).getMeasuringUnit(),actualIngredients.get(1).getMeasuringUnit());

  }

  @Test
  void getPortionsShouldReturnCorrectPortions() {
    int expectedPortions = 4;

    int actualPortions = recipe1.getPortions();

    assertEquals(expectedPortions, actualPortions);
  }







}
