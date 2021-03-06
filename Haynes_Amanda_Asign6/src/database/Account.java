package database;

/**
 * represents a single account in a credit card database
 * 
 * @author Amanda Hill
 *
 */
public class Account {

	private String name;
	private String accountNumber;
	private double creditLimit;
	private double balance;


	/**
	 * Default constructor for class Account
	 * @param n name
	 * @param aN account number
	 * @param cL credit limit
	 * @param bal current balance
	 */
	public Account (String n, String aN, double cL, double bal)
	{
		name = n;
		accountNumber = aN;
		creditLimit = cL;
		balance = bal;

	}


	public String toString()
	{
		String format = "%-30s %-16s %12.2f %8.2f \n";
		String accountString = String.format(format, name, accountNumber, creditLimit, balance );

		return accountString;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
