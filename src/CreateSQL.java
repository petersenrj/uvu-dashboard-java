import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateSQL 
{
	private int index = 1;
	private int index1 = 1;
	public CreateSQL()
	{
		
	}
	
	public void insert(ArrayList<Company> list, int location, Connection conn, int type, int errorCode)
	{
		String sql = null;
		if(list != null)
		{
			Company temp = list.get(location);
			
			if(type == 0)
			{
				sql = "INSERT INTO RealEstate (invName,capRate,netOp,debtCov,opExp,cashRet) " +
		                "VALUES ('" + temp.getName() + "'," + " '" + temp.getCapRate() + "', " + temp.getNetOperating() + ",  " + temp.getDebtCoverage()
		                + ",  " + temp.getOperatingExpense() + ", " + temp.getCashOnCash() + " );"; 
				Statement stmt = null;
				try {
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		if(type == 1)
		{
			sql = "INSERT OR REPLACE INTO AppInfo (ID, errorCode, username, password) " +
	                "VALUES (" + this.index1++ +", " + errorCode + ", null, null );"; 
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

