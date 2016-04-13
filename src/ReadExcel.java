import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel 
{
	private String name;
	private Double capRate;
	private Double netOperating;
	private Double debtCoverage;
	private Double operatingExpense;
	private Double cashOnCash;
	private ArrayList<Company> list;
	int errorCode = -1;
	
	public ReadExcel(ArrayList<Company> list)
	{
		this.list = list;
	}
	
	public ArrayList<Company> readExcel(File file) throws IOException
	{
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			errorCode = 0;
			return null;
		}
		
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		
		//XSSFSheet ratioSheet = workbook.getSheet("Ratios");
		XSSFSheet balanceSheet = workbook.getSheet("Balance Sheet");
		XSSFSheet profitSheet = workbook.getSheet("Profit and Loss");
		XSSFSheet cashFlowSheet = workbook.getSheet("Cash Flow Statement");
		
		
		
		if(balanceSheet == null || profitSheet == null || cashFlowSheet == null)
		{
			errorCode = 1;
			workbook.close();
			return null;
		}
		
		int colNumProfit = profitSheet.getRow(0).getLastCellNum();
		
		
		int rowNumProfit = profitSheet.getLastRowNum() + 1;
		
		
		
		Float netOrdinaryIncome = null;
		Float total80200InterestExpense = null;
		Float totalExpense = null;
		Float totalIncome = null;
		
		Float fixedAssets11000 = null;
		Float totalCurrentLiabilities = null;
		Float membersEquity30200 = null;
		
		Float netCashProvidedByOperatingActivities = null;
	
		
		name = balanceSheet.getRow(0).getCell(0).toString();
		
		for(int i = 0; i < rowNumProfit-1; i++)
		{
			XSSFRow profitRow = profitSheet.getRow(i);
			for(int j = 0; j < colNumProfit-1; j++)
			{
				
				XSSFCell cellProfit = profitRow.getCell(j);
				String temp = cellProfit.toString();
				
				if(temp.equals("Net Ordinary Income"))
					{
						
						XSSFRow temp1 = profitSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 2;
							workbook.close();
							return null;
						}
						int tempCol = colNumProfit - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						netOrdinaryIncome = Float.parseFloat(stringTemp);	
					}
					else if(temp.equals("Total Expense"))
					{
						XSSFRow temp1 = profitSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 1;
							workbook.close();
							return null;
						}
						int tempCol = colNumProfit - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						totalExpense = Float.parseFloat(stringTemp);
					}
					else if(temp.equals("Total 80200 · Interest Expense"))
					{
						XSSFRow temp1 = profitSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 1;
							workbook.close();
							return null;
						}
						int tempCol = colNumProfit - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						total80200InterestExpense = Float.parseFloat(stringTemp);
					}
					else if(temp.equals("Total Income"))
					{
						XSSFRow temp1 = profitSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 1;
							workbook.close();
							return null;
						}
						int tempCol = colNumProfit - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						totalIncome = Float.parseFloat(stringTemp);
					}
			}
		}
		
		
		int colNumBalance = balanceSheet.getRow(0).getLastCellNum();
		
		
		int rowNumBalance = balanceSheet.getLastRowNum() + 1;

		for(int i = 0; i < rowNumBalance-1; i++)
		{
			XSSFRow balanceRow = balanceSheet.getRow(i);
			for(int j = 0; j < colNumBalance-1; j++)
			{
				
				XSSFCell cellBalance = balanceRow.getCell(j);
				String temp = cellBalance.toString();
				
				if(temp.equals("11000 · Fixed Assets"))
					{
						XSSFRow temp1 = balanceSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 1;
							workbook.close();
							return null;
						}
						int tempCol = colNumBalance - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						fixedAssets11000 = Float.parseFloat(stringTemp);	
					}
					else if(temp.equals("Total Current Liabilities"))
					{
						XSSFRow temp1 = balanceSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 1;
							workbook.close();
							return null;
						}
						int tempCol = colNumBalance - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						totalCurrentLiabilities = Float.parseFloat(stringTemp);
					}
					else if(temp.equals("30200 · Member's Equity"))
					{
						XSSFRow temp1 = balanceSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 1;
							workbook.close();
							return null;
						}
						int tempCol = colNumBalance - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						membersEquity30200 = Float.parseFloat(stringTemp);
					}
			}
		}
		
		
		int colNumCash = cashFlowSheet.getRow(0).getLastCellNum();
		
		
		int rowNumCash = cashFlowSheet.getLastRowNum() + 1;

		for(int i = 0; i < rowNumCash-1; i++)
		{
			XSSFRow cashRow = cashFlowSheet.getRow(i);
			for(int j = 0; j < colNumCash-1; j++)
			{
				
				XSSFCell cellCash = cashRow.getCell(j);
				String temp = cellCash.toString();
				
				if(temp.equals("Net cash provided by Operating Activities"))
					{
						XSSFRow temp1 = cashFlowSheet.getRow(i);
						if(temp1 == null)
						{
							errorCode = 1;
							workbook.close();
							return null;
						}
						int tempCol = colNumCash - 1;
						XSSFCell cell = temp1.getCell(tempCol);
						String stringTemp = cell.getRawValue();
						netCashProvidedByOperatingActivities = Float.parseFloat(stringTemp);	
					}
			}
		}
		
		if(netCashProvidedByOperatingActivities == null || netOrdinaryIncome == null ||
		total80200InterestExpense == null || totalExpense == null || totalIncome == null
		|| fixedAssets11000 == null || totalCurrentLiabilities == null || membersEquity30200 == null)
		{
			errorCode = 3;
			workbook.close();
			return null;
		}
		
		NumberFormat formatter = new DecimalFormat("##.##");   
		
		Float capRateTemp = netOrdinaryIncome/fixedAssets11000;
		
		capRate = Double.parseDouble(formatter.format(capRateTemp*100));
		
		netOperating = Double.parseDouble(formatter.format(netOrdinaryIncome));
		
		debtCoverage = Double.parseDouble(formatter.format(netCashProvidedByOperatingActivities/(totalCurrentLiabilities+total80200InterestExpense)));
		
		Float operatingExpenseTemp = totalExpense/totalIncome;
		operatingExpense = Double.parseDouble(formatter.format(operatingExpenseTemp*100));
		
		Float cashOnCashTemp = netCashProvidedByOperatingActivities/membersEquity30200;
		cashOnCash = Double.parseDouble(formatter.format(cashOnCashTemp * 100));
		
		
		//System.out.println(totalIncome);
		
		
		Company temp = new Company(name, capRate, netOperating, debtCoverage, operatingExpense, cashOnCash);
		
		list.add(temp);
		
		workbook.close();
		
		return list;
	}
	
	public int getErrorCode()
	{
		return errorCode;
	}
}
