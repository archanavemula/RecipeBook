package application.db;


/**
 * 
 */
import java.sql.*;  
public class MySqlcon{ 
	
	
public static void main(String args[]){  
	
		try{  
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/javabook?characterEncoding=latin1&useConfigs=maxPerformance","archana","tiger"); 
			//url=jdbc:mysql://localhost:3306/hybrisdb?characterEncoding=latin1&useConfigs=maxPerformance
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from tutorials_tbl");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close(); 
		
		}catch(Exception e){ 
			
			e.printStackTrace();
			System.out.println(e);
			
		}  
}  
}  