import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws SQLException {
		Connection conn;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\The Beast\\Desktop\\2ndStoryCapitalMasterFolder\\2ndStoryCapitalMaster.sqlite");
		conn = DriverManager.getConnection("jdbc:sqlite:/Users/petersenrr/Desktop/2ndStoryCapitalMasterFolder/2ndStoryCapitalMaster.sqlite");
		if(conn != null)
		{
			Statement stmt = null;
			stmt = conn.createStatement();
			
		    String sql = "DROP TABLE RealEstate"; 
		    stmt.executeUpdate(sql);
		}
	}

}
