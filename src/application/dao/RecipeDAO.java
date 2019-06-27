package application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.rowset.CachedRowSetImpl;

import application.db.DBUtil;
import application.pojo.CookingTime;
import application.pojo.Directions;
import application.pojo.Ingredient;
import application.pojo.Recipe;
import application.pojo.Review;
/**
 * 
 * @author archana
 *
 */
public class RecipeDAO {

	
	public static void setAutoCommitFalse() {}
	public static void CommitChangesNow() {
		
		try {
			DBUtil.setCommit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static ResultSet selectRecipe() throws Exception {
		 String selStmt =  "SELECT * FROM RECIPE;" ;
		 ResultSet crs = null;
		 System.out.println(selStmt);
		 //Execute DELETE operation
		 try {
			  crs = DBUtil.dbExecuteQuery(selStmt);
		 } catch (SQLException | ClassNotFoundException e) {
			 System.out.print("Error occurred while DELETE Operation: " + e);
			 throw e;
		 }
		 
		 return crs;
	}
	
	public static ArrayList<Recipe> returnRecipeList(ResultSet crs) {
		ArrayList<Recipe> recipelist = new ArrayList<>();
		
		try {
			if(crs!=null) {
			while (crs.next()) {
				Recipe recipe = new Recipe();
				recipe.setIngredients(crs.getString(1));
				recipe.setDirections(crs.getString(2));
				recipe.setCookingTime(crs.getString(3));
				recipe.setReview(new Integer(crs.getString(4)));
				recipe.setName(crs.getString(5));
				recipelist.add(recipe);
			  
			}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return recipelist;
	}
	
	public static void deleteRecipe() throws Exception {
		 String delStmt =  "DELETE FROM RECIPE;" ;

		 System.out.println(delStmt);
		 //Execute DELETE operation
		 try {
			 DBUtil.dbExecuteUpdate(delStmt);
		 } catch (SQLException | ClassNotFoundException e) {
			 System.out.print("Error occurred while DELETE Operation: " + e);
			 throw e;
		 }
	}
	
	public static void insertRecipe(String ing, String dirs, String cts,String review,String recipeName) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =  "INSERT INTO Recipe (INGREDIENTS, DIRECTIONS, COOKINGTIME, REVIEW, RECIPENAME)" +
                        "VALUES" +"('"+ing+"','"+ dirs+ "','" +cts+"','"+review+"','"+recipeName+ "');" ;

        System.out.println(updateStmt);
        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

}
