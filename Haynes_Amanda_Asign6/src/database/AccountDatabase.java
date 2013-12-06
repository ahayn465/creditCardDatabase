
package database;


import java.util.*;


/**
 * Database to hold credit card accounts. An account will have a unique card number, a name, a credit limit
 * and a balance. 
 * 
 * @author Amanda Hill
 *
 */
public class AccountDatabase {
	
	 /**
	  * Internal comparator class to assist in alphabetical printing of the database.
	  * 
	 * @author Amanda Hill
	 *
	 */
	
	private class MyComparable implements Comparator<Account>
	    {
	        
	        public int compare(Account o1, Account o2) 
	        {
	            int res = 0;
	            if(o1.getName().compareToIgnoreCase(o2.getName()) < 0) res = -1;
	            else if(o1.getName().compareToIgnoreCase(o2.getName()) > 0) res = 1;
	            else res = 0;

	            return res;
	        }
	    } 

	HashMap<String, Account> accounts; // credit card database

	/**
	 * Constructor for class AccountDatabase initializes an empty card database
	 */
	public AccountDatabase()
	{
		accounts = new HashMap<String, Account>();
	}


	/**
	 * Adds an account to the database. Checks to see if the database contains the card number first.
	 * 
	 * @param key the account number
	 * @param account	the Account object
	 * @return true for a successful add, false if the database contained an account with that number
	 */
	public boolean add(String key, Account account)
	{
		boolean success = false;

		if(!containsKey(key))
		{
			accounts.put(key, account);
			success = true;

		}
		return success;


	}

	/**
	 * Deletes an entry from the database. Verifies that the database contains an account with that number. 
	 * 
	 * @param key the account number
	 * @return	true if the account was deleted, false if the account did not exist
	 */
	public boolean remove(String key)
	{
		boolean success = false;

		if(containsKey(key))
		{
			accounts.remove(key);
			success = true;
		}

		return success;
	}

	/**
	 * Returns an account from the database. 
	 * 
	 * @param key the account number
	 * @return the account if it exists, false if it does not
	 */
	public Account find (String key)
	{
		Account temp = null;

		temp = accounts.get(key);

		return temp;
	}

	/**
	 * Checks the database for a specific account 
	 * 
	 * @param key the account number
	 * @return true if the account exists, false otherwise
	 */
	public boolean containsKey(String key)
	{

		return accounts.containsKey(key);

	}

	/**
	 * Returns an alphabetically sorted array of the entire database by first name
	 * 
	 * @return the sorted array
	 */
	public ArrayList<Account> getSortedList()
	{
		ArrayList<Account> allAccounts = new ArrayList<Account>(accounts.values());

		Collections.sort(allAccounts, new MyComparable());
		
		return allAccounts;
	}
}
