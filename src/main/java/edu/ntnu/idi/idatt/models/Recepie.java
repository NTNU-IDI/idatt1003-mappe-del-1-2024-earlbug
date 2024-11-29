package edu.ntnu.idi.idatt.models;

import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.ArrayList;

/**
 * The <code>Recipe</code> class represents a real recipe. It stores information about what is
 *    needed to make a dish in terms of name, description, procedure and ingredients.
 *
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.6.0
 */
public class Recepie {
  String name;
  String description;
  String procedure;
  ArrayList<Grocery> ingredientList;

  /**
   * Constructor for <code>Recipe</code> class.
   *
   * @param name the name of the dish this recipe makes
   * @param description a description of what this recipe makes
   * @param procedure how to make the dish
   * @param ingredientList a list of all the ingredients and amount which is needed to make the
   *                       dish.
   */
  public Recepie(String name, String description, String procedure,
      ArrayList<Grocery> ingredientList) {
    try {
      ParamValidators.validateString(name);
      ParamValidators.validateString(description);
      ParamValidators.validateString(procedure);
      ParamValidators.validateGroceryArrayList(ingredientList);

      setName(name);
      setDescription(description);
      setProcedure(procedure);
      setIngredientList(ingredientList);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Grocery could not be made:\n" + e.getMessage());
    }

  }

  // Getters
  /**
   * Gets the name of the recipe.
   *
   * @return the name of the recipe as a <code>String</code>
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of the recipe.
   *
   * @return the description of the recipe as a <code>String</code>
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the procedure of the recipe.
   *
   * @return the procedure of the recipe as a <code>String</code>
   */
  public String getProcedure() {
    return this.procedure;
  }

  /**
   * Gets the list of ingredients of the recipe.
   *
   * @return the list of ingredients of the recipe as an <code>ArrayList</code>
   */
  public ArrayList<Grocery> getIngredientList() {
    return this.ingredientList;
  }


  // Setters
  private void setName(String name) throws IllegalArgumentException {
    ParamValidators.validateString(name);
    this.name = name;
  }

  private void setDescription(String description) throws IllegalArgumentException {
    ParamValidators.validateString(description);
    this.description = description;
  }

  private void setProcedure(String procedure) throws IllegalArgumentException {
    ParamValidators.validateString(procedure);
    this.procedure = procedure;
  }

  private void setIngredientList(ArrayList<Grocery> ingredientList)
      throws IllegalArgumentException {
    ParamValidators.validateGroceryArrayList(ingredientList);
    this.ingredientList = ingredientList;
  }
}
