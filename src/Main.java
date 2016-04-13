import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {

	private static ArrayList<Company> list = new ArrayList<>();
	private static int errorCode = -1;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		
		File file = null;
        if (0 < args.length)
        {
            file = new File(args[0]);
        }
		
		//String file = "testingExcel.xlsx";
		Connection conn;
		CreateSQL create;
		int index = 0;
		ReadExcel read = new ReadExcel(list);
		
		list = read.readExcel(file);
		
		create = new CreateSQL();
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\The Beast\\Desktop\\2ndStoryCapitalMasterFolder\\2ndStoryCapitalMaster.sqlite");
			//conn = DriverManager.getConnection("jdbc:sqlite:/Users/petersenrr/Desktop/2ndStoryCapitalMasterFolder/2ndStoryCapitalMaster.sqlite");
			if(conn != null)
			{
				errorCode = -1;
				Statement stmt = null;
				stmt = conn.createStatement();
				
//				
				
				
			      String sql = "CREATE TABLE IF NOT EXISTS RealEstate " +
			                   "(invName           VARCHAR(50), " +
			                   " capRate           REAL, " + 
			                   " netOp            REAL, " + 
			                   " debtCov        REAL, " + 
			                   " opExp         REAL," +
			                   " cashRet        REAL)"; 
			      stmt.executeUpdate(sql);
			      
//			      sql = "CREATE TABLE IF NOT EXISTS ErrorInfo " +
//		                   "(ID INT PRIMARY KEY     NOT NULL," +
//		                   " errorCode           INT)"; 
//			      stmt.executeUpdate(sql);
			      
			      
			      sql = "CREATE TABLE IF NOT EXISTS AppInfo " +
		                   "(ID INT PRIMARY KEY     NOT NULL," +
		                   "errorCode           INT," + 
		                   "username        VARCHAR(20)," +
		                   "password        VARCHAR(30))"; 
			      stmt.executeUpdate(sql);
			      
			      errorCode = read.getErrorCode();
			      
			      
			      create.insert(list, index, conn, 0, errorCode);
			      
			      errorCode = read.getErrorCode();
			      
			      create.insert(list, index, conn, 1, errorCode);
			      
			      
			      stmt.close();
			      conn.close();
			}
			else
			{
				errorCode = 0;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			errorCode = 0;
		}
		
		
		
		
		
		
//		FileInputStream in = new FileInputStream(new File("test.xlsx"));
//		
//		XSSFWorkbook workbook = new XSSFWorkbook(in);
//		
//		XSSFSheet sheet = workbook.getSheet("Sheet1");
//		
//		int colNum = sheet.getRow(0).getLastCellNum();
//		
//		XSSFRow row = sheet.getRow(0);
//		
//		int cost =0, taxes=0, income=0;
//		for(int i = 0; i < colNum; i++)
//		{
//			
//			XSSFCell cell = row.getCell(i);
//			String temp = cell.toString();
//			if(temp.equals("Cost"))
//			{
//				cost = i;
//			}
//			else if(temp.equals("taxes"))
//			{
//				taxes = i;
//			}
//			else if(temp.equals("monthly rental income"))
//			{
//				income = i;
//			}
//			
//		}
//		
//		int rowNum = sheet.getLastRowNum() + 1;
//		
//		calculateIndividualReturn calc = new calculateIndividualReturn();
//		
//		ArrayList<Double> returns = new ArrayList<Double>();
//		
//		
//		int cost1 = 0, taxes1 = 0, income1 = 0;
//		for(int j = 1; j < rowNum; j++)
//		{
//			row = sheet.getRow(j);
//			XSSFCell cellCost = row.getCell(cost);
//			XSSFCell cellTaxes = row.getCell(taxes);
//			XSSFCell cellIncome = row.getCell(income);
//			String costString = cellCost.toString();
//			String costTaxes = cellTaxes.toString();
//			String costIncome = cellIncome.toString();
//			double tempCost = Double.parseDouble(costString);
//			double tempTaxes = Double.parseDouble(costTaxes);
//			double tempIncome = Double.parseDouble(costIncome);
//			
//			returns.add(calc.calculate(tempCost, tempTaxes, tempIncome));
//		}
//		
//		System.out.println("Property 1 = " + returns.get(0));
//		System.out.println("Property 2 = " + returns.get(1));
//		System.out.println("Property 3 = " + returns.get(2));
//		System.out.println("Property 4 = " + returns.get(3));
		
		
		
		
		

		
		

	}

}
