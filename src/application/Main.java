package application;
	
import application.recipe.RecipeBookCoordinator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * @author archana
 *
 */
public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
       // System.out.println(new URL(tempFile.getPath()));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("recipe.fxml"));
        
        Parent root = loader.load();
        RecipeBookCoordinator recipeController = loader.getController();

        primaryStage.setTitle("RecipeBook");
       // primaryStage.setMaximized(true);

        primaryStage.setScene(new Scene(root));
        primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                recipeController.saveChanges();
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    
	/*@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	

}
