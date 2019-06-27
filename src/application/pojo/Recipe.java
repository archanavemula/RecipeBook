package application.pojo;

public class Recipe implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// class variables for the recipe
    private Ingredient ings;
    private Directions dirs;
    private CookingTime cts;
    private Review rv;
    private String name;

    // constructor for the recipe class, gets the required components and assembles the recipe
    public Recipe(String recipeName, Ingredient ingredients, Directions directions, CookingTime ct, Review review) {
       // System.out.println("Assembling the new recipe!");
        this.ings = ingredients;
        this.dirs = directions;
        this.cts = ct;
        this.rv = review;
        this.name = recipeName;
    }

    public Recipe() {
       // System.out.println("Assemble recipe without given ingredients.");
    }

    public String getName() {
        String recipePrint;

        recipePrint = name;

        return recipePrint;
    }

    public String getReview() {
        String review = new String(Integer.toString(rv.returnStars()));
        return review;
    }

    public String getCookingTime() {
        String cook = cts.returnTime();
        return cook;
    }

    public String getDirections() {
        String directions = dirs.returnDirections();
        return directions;
    }

    public String getIngredients() {
        String outIngredients = "";
        outIngredients += ings.returnIngredient() + "\n";
        return outIngredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReview(int rv) {
        this.rv = new Review(new Integer(Integer.toString(rv)));
    }

    public void setCookingTime(String cookingTime) {
        this.cts = new CookingTime(cookingTime);
    }

    public void setDirections(String directions) {
        this.dirs = new Directions(directions);
    }

    public void setIngredients(String ingredients) {
        this.ings = new Ingredient(ingredients);
    }

    public String toString() {
        String recipePrint = "";

        String steps = dirs.returnDirections();

        recipePrint += (name + "\n\n");
        recipePrint += ("Cooking time: " + cts.returnTime() + " minutes\n\n");
        recipePrint += ("Ingredients:\n");
        recipePrint += (ings.returnIngredient() + "\n\n");
        recipePrint += ("recipe.Directions:\n");
        recipePrint += dirs.returnDirections();
        recipePrint += ("\n");
        recipePrint += ("recipe.Review: " + rv.returnStars() + "/5");

        return recipePrint;
    }
}
