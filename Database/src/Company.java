
public class Company 
{
	private String name;
	private double capRate;
	private double netOperating;
	private double debtCoverage;
	private double operatingExpense;
	private double cashOnCash;
	
	public Company(String name, double capRate, double netOperating, double debtCoverage, double operatingExpense, double cashOnCash)
	{
		this.name = name;
		this.capRate = capRate;
		this.netOperating = netOperating;
		this.debtCoverage = debtCoverage;
		this.operatingExpense = operatingExpense;
		this.cashOnCash = cashOnCash;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getCapRate()
	{
		return capRate;
	}
	
	public double getNetOperating()
	{
		return netOperating;
	}
	
	public double getDebtCoverage()
	{
		return debtCoverage;
	}
	
	public double getOperatingExpense()
	{
		return operatingExpense;
	}
	
	public double getCashOnCash()
	{
		return cashOnCash;
	}

}
