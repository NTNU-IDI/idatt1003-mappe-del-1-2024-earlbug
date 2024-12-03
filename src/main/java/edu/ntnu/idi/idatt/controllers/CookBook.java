package edu.ntnu.idi.idatt.controllers;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.ArrayList;

/**
 * The <code>CookBook</code> class is responsible for storing <code>Recipe</code> instances in an
 *    <Code>ArrayList</Code>. It represents a cook book where it is possible to add and read
 *    recipes.
 *
 * @since 0.6.2
 * @author Erlend Sundsdal
 * @version 0.7.0
 */
public class CookBook {

  ArrayList<Recipe> recipeList;

  /**
   * Constructor for <code>CookBook</code> class, which initializes an <code>ArrayList</code>
   *    for the <code>Recipes</code> to be stored.
   */
  public CookBook() {
    recipeList = new ArrayList<>();
  }

  /**
   * Makes a <code>Recipe</code> and adds it to the list of recipes.
   *
   * @param name the name of the new <code>Recipe</code>
   * @param description a description of the dish
   * @param procedure instructions on how to make the dish
   * @param ingredientList an <code>ArrayList</code> of all the necessary ingredients
   * @param portions how many portion one dish yields
   * @throws IllegalArgumentException if any of the arguments are invalid
   */
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

  /**
   * Gets the list of all the <code>Recipe</code> instances.
   *
   * @return an <code>ArrayList</code> of all the <code>Recipe</code> instances.
   */
  public ArrayList<Recipe> getRecipeList() {
    return this.recipeList;
  }

  public Recipe getRecipeByName(String inpName) {
    return recipeList.stream()
        .filter(recipe -> recipe.getName().equalsIgnoreCase(inpName))
        .findFirst()
        .orElse(null);
  }
}
