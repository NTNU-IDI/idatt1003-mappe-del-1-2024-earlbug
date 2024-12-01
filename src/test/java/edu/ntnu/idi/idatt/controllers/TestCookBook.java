package edu.ntnu.idi.idatt.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TestCookBook {

  @Test
  void addRecipeShouldAddRecipeToList() {
    CookBook cookBook = new CookBook();
    String name = "scrambled Eggs";
    String description = "This recipe shows you how to make scrambled eggs.";
    String procedure = "Break your eggs into a hot frying pan. The scramble them. Voilà";
    Grocery egg = new Grocery("egg", 2, "units");
    int portions = 4;
    ArrayList<Grocery> ingredientList = new ArrayList<>();
    ingredientList.add(egg);

    cookBook.addRecipe(name, description, procedure, ingredientList, portions);

    assertEquals(1, cookBook.getRecipeList().size());
    Recipe recipe = cookBook.getRecipeList().get(0);
    assertEquals(name, recipe.getName());
    assertEquals(description, recipe.getDescription());
    assertEquals(procedure, recipe.getProcedure());
    assertEquals(ingredientList, recipe.getIngredientList());
    assertEquals(portions, recipe.getPortions());
  }
}
