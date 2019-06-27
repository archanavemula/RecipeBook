package application.pojo;

import java.util.Scanner;

public class Ingredient implements java.io.Serializable {
    private String ingredient;

    public Ingredient(String ingredientName) {
       // System.out.println("This is the ingredient constructor!");
        this.ingredient = ingredientName;
    }

    public Ingredient() {
       // System.out.println("This is the ingredient constructor!");
    }

    public String returnIngredient() {
        return this.ingredient;
    }
}
