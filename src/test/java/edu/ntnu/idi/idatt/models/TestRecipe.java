package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @since 0.2.0
 * @author Erlend Sundsdal
 * @version 0.6.0
 */

public class TestRecipe {

  @BeforeEach
  void setup() {

  }

  @Test
  void testRecipeConstructor() {
    // Arrange
    String name = "scrambled Eggs";
    String description = "This recipe shows you how to make scrambled eggs.";
    String procedure = "Break your eggs into a hot frying pan. The scramble them. Voilà";
    Grocery egg = new Grocery("egg", 2, "units");

    ArrayList<Grocery> ingredientList = new ArrayList<>();
    ingredientList.add(egg);

    // Act
    Recipe recipe = new Recipe(name, description,procedure, ingredientList);

    // Assert
    assertEquals(name, recipe.getName());
    assertEquals(description, recipe.getDescription());
    assertEquals(procedure, recipe.getProcedure());
    assertEquals(ingredientList, recipe.getIngredientList());
  }

}
