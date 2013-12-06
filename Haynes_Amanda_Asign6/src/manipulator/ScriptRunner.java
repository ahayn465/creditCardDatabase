
package manipulator;

import java.io.*;
import java.util.Scanner;
import database.Account;
import userInterface.ConsoleCom;

/**
 * Opens a file and executes database operations based on the contents of the file. Operations supported include:
 * add account, delete account, make purchase and set new credit limit.
 * 
 * @author Amanda Hill
 * @version December 5, 2013
 */
public class ScriptRunner {

	private DatabaseManipulator scriptManip;
	private ConsoleCom com = new ConsoleCom();


	public ScriptRunner(DatabaseManipulator scriptManip)
	{
		this.scriptManip = scriptManip;
	}

	public void runScript() 
	{
		Scanner fileReader = null;
		FileReader file;
		String line = null;
		boolean done = false;
		
		try
		{
		String fileName = com.getInputString("enter file name: ");
		file = new FileReader(fileName); 
		fileReader = new Scanner(file);

		}
		catch (FileNotFoundException e)
		{
			com.println("invalid file name");
		}

		if(fileReader.hasNext())
			line = fileReader.nextLine();

		while(line != null)
		{
			
			int doThis = 0;
			
			if(line.equals("cre"))			//I had to do this because I started this project in version 1.6
				doThis = 1;					//which does not support switch statements with String input
			else if(line.equals("del"))		//when I tried to change the version of the project it wrecked
				doThis = 2;					//my code :( poop!
			else if (line.equals("lim"))
				doThis = 3;
			else if (line.equals("pur"))
				doThis = 4;

			switch (doThis)
			{
			case 1: //ADD ACCOUNT
				String account = fileReader.nextLine();
				String name = fileReader.nextLine();
				String l = fileReader.nextLine();
					double limit = Double.parseDouble(l);	//convert string representation of limit to integer
				String b = fileReader.nextLine();			//ASSUMES CORRECT FILE FORMAT
					double balance = Double.parseDouble(b);	//convert string representation of balance to integer

				Account newAccount = new Account (name, account, limit, balance);
				scriptManip.addAccount(newAccount);
				
				if(fileReader.hasNext())
					line = fileReader.nextLine();
				else
					done = true;
				break;

			case 2:	//DELETE ACCOUNT
				String accountToDelete = fileReader.nextLine();
				scriptManip.deleteAccount(accountToDelete);
				
				if(fileReader.hasNext())
					line = fileReader.nextLine();
				else
					done = true;
				break;

			case 3://CHANGE CREDT LIMIT
				String cardLim = fileReader.nextLine();
				String lim = fileReader.nextLine();
				double newLimit = Double.parseDouble(lim);

				scriptManip.changeLimit(cardLim, newLimit);
				
				if(fileReader.hasNext())
					line = fileReader.nextLine();
				else
					done = true;
				break;

			case 4: //MAKE PURCHASE
				String cardPur = fileReader.nextLine();
				String a = fileReader.nextLine();
					double amount = Double.parseDouble(a); //convert string representation of purchase amount
														   //to integer value, ASSUMES CORRECT FILE FORMAT
				scriptManip.makePurchase(cardPur, amount);

				if(fileReader.hasNext())
					line = fileReader.nextLine();
				else
					done = true;
				break;

			}
			if (done == true)	//if we have exhausted all the lines in the file, exit the loop
				break;
		}

	}
}


