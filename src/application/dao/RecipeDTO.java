package application.dao;

import application.pojo.CookingTime;
import application.pojo.Directions;
import application.pojo.Ingredient;
import application.pojo.Review;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author archana
 *
 */
public class RecipeDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// class variables for the recipe
    private SimpleObjectProperty<Ingredient> ingredients;
    private SimpleObjectProperty<Directions> directions;
    private SimpleObjectProperty<CookingTime> cts;
    private SimpleObjectProperty<Review> review;
    private StringProperty recipeName;

    // constructor for the recipe class, gets the required components and assembles the recipe
    public RecipeDTO() {
        System.out.println("Assembling the new recipe!");
        this.ingredients = new SimpleObjectProperty<>();
        this.directions = new SimpleObjectProperty<>();
        this.cts = new SimpleObjectProperty<>();
        this.review = new SimpleObjectProperty<>();
        this.recipeName = new SimpleStringProperty();;
    }


    public Ingredient getIngredients() {
		return ingredients.get();
	}


	public void setIngredients(Ingredient ingredients) {
		this.ingredients.set(ingredients);
	}

	public SimpleObjectProperty ingredientsProperty(){
        return ingredients;
    }
 

	public Directions getDirections() {
		return directions.get();
	}


	public void setDirections(Directions directions) {
		this.directions.set(directions);
	}
	
	public SimpleObjectProperty directionsProperty(){
        return directions;
    }


	public CookingTime getCts() {
		return cts.get();
	}


	public void setCts(CookingTime cts) {
		this.cts.set(cts); 
	}

	public SimpleObjectProperty ctsProperty(){
        return cts;
    }


	public Review getReview() {
		return review.get();
	}


	public void setReview(Review review) {
		this.review.set(review);
	}

	public SimpleObjectProperty reviewProperty(){
        return review;
    }


	public String getRecipeName() {
		return recipeName.get();
	}


	public void setRecipeName(String recipeName) {
		this.recipeName.set(recipeName);
	}

	public StringProperty recipeNameProperty(){
        return recipeName;
    }


	public String toString() {
        String recipePrint = "";

        String steps = directions.get().returnDirections();

        recipePrint += (recipeName + "\n\n");
        recipePrint += ("Cooking time: " + cts.get().returnTime() + " minutes\n\n");
        recipePrint += ("Ingredients:\n");
        recipePrint += (ingredients.get().returnIngredient() + "\n\n");
        recipePrint += ("recipe.Directions:\n");
        recipePrint += directions.get().returnDirections();
        recipePrint += ("\n");
        recipePrint += ("recipe.Review: " + review.get().returnStars() + "/5");

        return recipePrint;
    }

	
	
}

