package application.db;

import java.sql.SQLException;
/**
 * 
 * 
 * @author archana
 *
 */
public class DBTest {

	
	public static void main(String[] args){
		try {
			DBUtil.dbConnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
