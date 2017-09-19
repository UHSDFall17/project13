package login;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login 
{
	static String fileName = "accounts.txt";
	public void login()
	{
		
		Console console = System.console();
		String user = console.readLine("Username: ");
		char[] pass = console.readPassword("Password: ");
		String pwd = new String(pass);
		if(authorization(user, pwd))
		{
			/*Access account.*/
			
		}
		else
		{
			System.out.println("Invalid username or password.");
		}
	}
	public boolean authorization(String user, String pass)
	{
		String line = null;
		boolean autherized = false;
		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null)
			{
				String [] token = line.split(" ");
				if(token[0].equals(user) && token[1].equals(pass))
				{
					autherized = true;
					return autherized;
				}
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found: '" + fileName + "'.");
		}
		catch(IOException e) 
		{
	        System.out.println("Error reading file: '" + fileName + "'.");                  
	    }
		return false;
	}
}

