package edu.ntnu.idi.idatt.controllers;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.ArrayList;

public class CookBook {

  ArrayList<Recipe> recipeList;

  public CookBook() {
    recipeList = new ArrayList<>();
  }

  public void addRecipe(String name, String description, String procedure,
      ArrayList<Grocery> ingredientList, int portions) {
    try {
      ParamValidators.validateString(name);
      ParamValidators.validateString(description);
      ParamValidators.validateString(procedure);
      ParamValidators.validateArrayList(ingredientList);
      ParamValidators.validatePositiveInt(portions);

      Recipe newRecipe = new Recipe(name, description, procedure, ingredientList, portions);
      recipeList.add(newRecipe);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Recipe could not be added:\n" + e.getMessage());
    }
  }

  public ArrayList<Recipe> getRecipeList() {
    return this.recipeList;
  }
}
