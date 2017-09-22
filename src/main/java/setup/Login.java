package main.java.setup;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login 
{
	protected void login()
	{
		Console console = System.console();
		String user = console.readLine("Username: ");
		char[] pass = console.readPassword("Password: ");
		String pwd = new String(pass);
		
		if(authorization(user.toLowerCase(), pwd))
		{
			/*Access account.*/
		}
	}
	private boolean authorization(String user, String pass)
	{
		String line = null;
		String fileName = "Accounts/" + user + "/loginInfo.txt";
		boolean autherized = false;
		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			if((line = bufferedReader.readLine()) != null)
			{
				String [] token = line.split(" ");
				if(token[0].equals(pass)) 
				{
					autherized = true;
					bufferedReader.close();
				}
				else
				{
					autherized = false;
					System.out.println("Password is invalid.");
					bufferedReader.close();
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("User not found: '" + fileName + "'.");
		}
		catch(IOException e) 
		{
	        System.out.println("Error reading file: '" + fileName + "'.");                  
	    }
		return autherized;
	}
}