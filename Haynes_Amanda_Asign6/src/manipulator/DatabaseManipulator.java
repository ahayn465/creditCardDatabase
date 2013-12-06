package manipulator;

import database.Account;
import java.util.ArrayList;
import database.AccountDatabase;

/**
 * Controller for credit card database contains methods to add and delete accounts, change credit limit, 
 * make a purchase, print all accounts in database and print only a selected account.
 * 
 * @author Amanda Hill
 * @version December 5, 2013
 *
 */
public class DatabaseManipulator {

	AccountDatabase db;	//hash table database of credit card accounts

	public DatabaseManipulator()
	{
		db = new AccountDatabase();
	}


	/**
	 * Checks to see if the database already contains an account with that number. Adds the account
	 * if it is not contained in the database already.
	 * 
	 * @param newAccount the account to add
	 * @return false if the database contains that card number already, true otherwise
	 */
	public boolean addAccount(Account newAccount)
	{
		boolean success = false;

		if(!db.containsKey(newAccount.getAccountNumber()))
		{	
			db.add(newAccount.getAccountNumber(), newAccount);
			success = true;
		}

		return success;		
	}


	/**
	 * Checks to see if the database contains an accountwith that card number. Deletes that account if it exists.
	 * 
	 * @param key account number 
	 * @return false if the database does not contain an account with that number
	 */
	public boolean deleteAccount(String key)
	{
		boolean success = true;

		if(db.containsKey(key))	
			db.remove(key); 
		else
			success = false;

		return success;
	}

	/**
	 * Helper method to return an Account
	 * 
	 * @param key account number
	 * @return	the specified Account if it exits, null otherwise
	 */
	private Account getAccount(String key)
	{
		Account holder = null;

		holder = db.find(key);


		return holder;	
	}

	/**
	 * Changes the balance on an account. Checks to see if the new limit is over the current balance first.
	 * 
	 * @param key the account number
	 * @param newCreditLimit the new limit
	 * @return true if the accounts limit was updated, false if the new limit was less than the current balance
	 */
	public boolean changeLimit(String key, double newCreditLimit)
	{
		boolean success = false;
		Account actToChange = getAccount(key);

		if(actToChange != null)	// make sure there is an account with that number
		{

			if(newCreditLimit > actToChange.getBalance())	//make sure the new limit is greater than the current balance
			{
				actToChange.setCreditLimit(newCreditLimit);
				success = true;
			}
		}

		return success;
	}	

	/**
	 * Makes a purchase on the account. Checks to see that the purchase is within the available credit limit. 
	 * 
	 * @param key the account number
	 * @param purchaseAmount the amount of the purchase
	 * @return true if the purchase was within the credit limit, false otherwise
	 */
	public boolean makePurchase(String key, double purchaseAmount)
	{

		Account purchaseAcct = getAccount(key);
		boolean success = false;

		if(purchaseAcct != null) //make sure there is an account with that number
		{

			double newBalance = purchaseAcct.getBalance() + purchaseAmount;

			if(newBalance < purchaseAcct.getCreditLimit() )	//make sure the purchase is within the limit
			{
				purchaseAcct.setBalance(newBalance);
				success = true;
			}
		}

		return success;
	} 

	/**
	 * Prints the selected account to the console
	 * 
	 * @param key the account number
	 */
	public void printOneAccount(String key)
	{
		Account acctToPrint = getAccount(key);
		if (acctToPrint != null)
			System.out.print(acctToPrint);
	}

	/**
	 * Prints all the accounts in the database to the console
	 */
	public void printAllAccounts()
	{
		ArrayList<Account> allAccounts = db.getSortedList();

		for(Account acct: allAccounts)
			System.out.print(acct);

	}
	
	public void saveToFile()
	{
		
	}
	
	public void readFromFile()
	{
		
	}
}




