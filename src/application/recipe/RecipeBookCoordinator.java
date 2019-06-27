package application.recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import com.google.gson.Gson;

import application.dao.RecipeDAO;
import application.pojo.CookingTime;
import application.pojo.Directions;
import application.pojo.Ingredient;
import application.pojo.Recipe;
import application.pojo.Review;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecipeBookCoordinator implements Initializable {

    @FXML private MenuBar myMenu;
    @FXML private ListView<Recipe> recipeList = new ListView<Recipe>();
    @FXML private Label recipeTitle;
    @FXML private VBox recipeMain;
    @FXML private VBox recipeListBox;
    @FXML private ScrollPane scrollPaneRecipe;
    @FXML private ObservableList<Recipe> recipes = FXCollections.observableArrayList();
    final File file = new File("/tmp/recipes.json");

    
    public void saveChanges() {
             
                try {
                	 RecipeDAO.deleteRecipe();
                for(int i =0; i < recipes.size(); i++) {
                    	Recipe rcp = recipes.get(i);	
					RecipeDAO.insertRecipe(rcp.getIngredients(), rcp.getDirections(), rcp.getCookingTime(), rcp.getReview(), rcp.getName());
                }
					//RecipeDAO.CommitChangesNow();
                } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
    }
    
    public static String removeAll(String recipeText,String type) {
    	if(recipeText!=null) {
    	if(type.equals("ing") || type.equals("dir")) {
    		if(recipeText.length() > 1000)
    		recipeText = recipeText.substring(0, 1000);
    	}
    	
    	if(type.equals("rev") || type.equals("time")) {
    		if(recipeText.length() > 20)
    		recipeText = recipeText.substring(0, 20);
    	}
    	
    	if(type.equals("name") ) {
    		if(recipeText.length() > 200)
    		recipeText = recipeText.substring(0, 200);
    	}
    	recipeText = recipeText.replaceAll("\"","");
    	recipeText = recipeText.replace(";","");
    	recipeText = recipeText.replace("&","");
    	recipeText = recipeText.replace("#","");
    	recipeText = recipeText.replace("#","");
    	
    	}
    	return recipeText;
    }
    
   /* public void saveChanges_original() {
        try {
            Gson gson = new Gson();

            try (final FileWriter fileWriter = new FileWriter(file)) {
                gson.toJson(recipes, fileWriter);
            }
            System.out.printf("Serialized data is saved in /tmp/recipes.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }*/

    @FXML
    protected void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) myMenu.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    protected void deleteallRecipes(ActionEvent actionEvent) throws Exception {
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Recipes");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete all your recipes?");
        //alert.showAndWait();
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        	recipes.remove(0, recipes.size());
            recipeList.getSelectionModel().selectFirst();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        
    	
    }

    @FXML
    protected void aboutRecipe(ActionEvent actionEvent) throws Exception {
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Recipe Book...");
        alert.setHeaderText(null);
        alert.setContentText("Recipe book is a kitchen reference containing recipes.Items Can be added ,deleted and stored.");
        alert.showAndWait();
        
    	
    }
    
    @FXML
    protected void addRecipe(ActionEvent actionEvent) throws Exception {
        // clear window for the inputs and disable the menu
        recipeMain.getChildren().clear();
        recipeListBox.setDisable(true);

        // update labels and set them on the page with inputs and validation
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.setErrorDecorationEnabled(false);
        recipeTitle.setText("New Recipe");

        Separator separateRecipeName = new Separator();

        Button backButton = new Button("Back");

        Label nameLabel = new Label("Recipe name: ");
        TextField name = new TextField();
        validationSupport.registerValidator(name, Validator.createEmptyValidator("Recipe name is required"));

        Label cookLabel = new Label("Total cooking time: ");
        TextField cook = new TextField();
        validationSupport.registerValidator(cook, Validator.createEmptyValidator("Cooking time is required"));

        Label ratingLabel = new Label("Rating: ");
        Rating rate = new Rating(5);
        rate.setUpdateOnHover(false);

        Label ingsLabel = new Label("Ingredients: ");
        TextArea ingredients = new TextArea();
        validationSupport.registerValidator(ingredients, Validator.createEmptyValidator("Ingredients are required"));

        Label dirsLabel = new Label("Directions: ");
        TextArea directions = new TextArea();
        validationSupport.registerValidator(directions, Validator.createEmptyValidator("Directions are required"));

        Button createRecipe = new Button("Create");
        recipeMain.getChildren().addAll(recipeTitle, separateRecipeName, backButton, nameLabel, name, cookLabel, cook, ratingLabel, rate, ingsLabel, ingredients, dirsLabel,
                directions, createRecipe);

        // the two buttons on the main pane
        // back button
        backButton.addEventHandler(ActionEvent.ACTION, (e) -> {
            recipeList.setDisable(false);
            recipeMain.getChildren().clear();
            recipeList.getSelectionModel().selectFirst();
        });

        // create button
        createRecipe.addEventHandler(ActionEvent.ACTION, (e) -> {
            // show validation errors if its invalid
            if (validationSupport.isInvalid()) {
                validationSupport.setErrorDecorationEnabled(true);
                validationSupport.redecorate();
            }
            // create recipe and move back to the main menu
            else {
                // add the recipe here
                String recipeName = removeAll(name.getText(),"name");
                Ingredient ings = new Ingredient(removeAll(ingredients.getText(),"ing"));
                Directions direct = new Directions(removeAll(directions.getText(),"dir"));
                CookingTime ct = new CookingTime(removeAll(cook.getText(),"time"));
                Review rv = new Review((int) rate.getRating());

                Recipe mb = new Recipe(recipeName, ings, direct, ct, rv);
                recipes.add(mb);

                // re-enable the menu and reload the screen
                recipeListBox.setDisable(false);
                recipeMain.getChildren().clear();
                recipeList.getSelectionModel().selectLast();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // deserialize and add recipes from saved data using GSON
       // Gson gson = new Gson();
       // try (final FileReader fileReader = new FileReader(file)) {
    	try {
            //ArrayList<Recipe> tempRecipes_old = gson.fromJson(fileReader, new TypeToken<ArrayList<Recipe>>() {}.getType());
            ResultSet crs = RecipeDAO.selectRecipe();
            ArrayList<Recipe> tempRecipes = RecipeDAO.returnRecipeList(crs);
            recipes = FXCollections.observableArrayList(tempRecipes);
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // update the list item with the new recipes
        recipeList.setItems(recipes);

        // add custom list item functionality
        recipeList.setCellFactory(param -> new ListCell<Recipe>() {
            @Override
            protected void updateItem(Recipe item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        // update the main view when selection is changed
        recipeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Recipe>() {
            @Override
            public void changed(ObservableValue<? extends Recipe> observable, Recipe oldValue, Recipe newValue) {
                recipeMain.getChildren().clear();
                if(newValue!=null)
                recipeTitle.setText(newValue.getName());
                recipeTitle.setStyle("-fx-font: 24 arial;");
                Separator separateRecipeName = new Separator();
                FlowPane textFlow = new FlowPane();
                if(newValue!=null) {
                Label recipeCookTime = new Label("Total cooking time: " + newValue.getCookingTime() + " minutes.\n");
                Label recipeReview = new Label("Your review: " + newValue.getReview() + "/5 stars.\n");
                Label recipeIngredients = new Label("Ingredients:\n\n" + newValue.getIngredients() + "\n");
                Label recipeDirections = new Label("Directions:\n\n" + newValue.getDirections());
                recipeIngredients.setMaxWidth(scrollPaneRecipe.USE_COMPUTED_SIZE);
                recipeDirections.setMaxWidth(scrollPaneRecipe.USE_COMPUTED_SIZE);    
                textFlow.getChildren().addAll(recipeCookTime, recipeReview, recipeIngredients, recipeDirections);
                textFlow.setOrientation(Orientation.VERTICAL);
                }
                
                Button deleteRecipe = new Button("Delete");
                Button editRecipe = new Button("Edit");

                // the two buttons on the main pane
                // delete button
                deleteRecipe.addEventHandler(ActionEvent.ACTION, (e) -> {
                    recipes.remove(newValue);
                    recipeList.getSelectionModel().selectFirst();
                });
                // edit button
                editRecipe.addEventHandler(ActionEvent.ACTION, (e) -> {
                    // clear window for the inputs and disable the menu
                    recipeMain.getChildren().clear();
                    recipeList.setDisable(true);

                    // update labels and set them on the page with inputs and validation
                    ValidationSupport validationSupport = new ValidationSupport();
                    validationSupport.setErrorDecorationEnabled(false);
                    recipeTitle.setText("Edit " + newValue.getName());

                    Button backButton = new Button("Back");

                    Label nameLabel = new Label("Recipe name: ");
                    TextField name = new TextField(newValue.getName());
                    validationSupport.registerValidator(name, Validator.createEmptyValidator("Recipe name is required"));

                    Label cookLabel = new Label("Total cooking time: ");
                    TextField cook = new TextField(newValue.getCookingTime());
                    validationSupport.registerValidator(cook, Validator.createEmptyValidator("Cooking time is required"));

                    Label ratingLabel = new Label("Rating: ");
                    Rating rate = new Rating(5);
                    rate.setRating((double) Integer.parseInt(newValue.getReview()));
                    rate.setUpdateOnHover(false);

                    Label ingsLabel = new Label("Ingredients: ");
                    TextArea ingredients = new TextArea(newValue.getIngredients().trim());
                    validationSupport.registerValidator(ingredients, Validator.createEmptyValidator("Ingredients are required"));

                    Label dirsLabel = new Label("Directions: ");
                    TextArea directions = new TextArea(newValue.getDirections().trim());
                    validationSupport.registerValidator(directions, Validator.createEmptyValidator("Directions are required"));

                    Button updateRecipe = new Button("Update");
                    recipeMain.getChildren().addAll(recipeTitle, separateRecipeName, backButton, nameLabel, name, cookLabel, cook, ratingLabel, rate, ingsLabel, ingredients, dirsLabel,
                            directions, updateRecipe);

                    // the two buttons on the main pane
                    // back button
                    backButton.addEventHandler(ActionEvent.ACTION, (f) -> {
                        recipeList.setDisable(false);
                        recipeMain.getChildren().clear();
                        recipeList.getSelectionModel().selectFirst();
                    });

                    // create button
                    updateRecipe.addEventHandler(ActionEvent.ACTION, (g) -> {
                        // show validation errors if its invalid
                        if (validationSupport.isInvalid()) {
                            validationSupport.setErrorDecorationEnabled(true);
                            validationSupport.redecorate();
                        }
                        // create recipe and move back to the main menu
                        else {
                            // add the recipe here
                            newValue.setName(name.getText());
                            newValue.setIngredients(ingredients.getText().trim());
                            newValue.setDirections(directions.getText().trim());
                            newValue.setCookingTime(cook.getText());
                            newValue.setReview((int) rate.getRating());

                            // re-enable the menu and reload the screen
                            int index = recipes.indexOf(newValue);
                            if (index >= 0) {
                                
                                recipes.set(index, newValue);
                            }
                            recipeList.setDisable(false);
                            recipeMain.getChildren().clear();
                            recipeList.getSelectionModel().selectFirst();
                        }
                    });
                });
                recipeMain.getChildren().addAll(recipeTitle, editRecipe, separateRecipeName, textFlow, deleteRecipe);
            }
        });
    }
}
