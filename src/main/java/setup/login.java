package setup;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class login 
{
	protected static void login()
	{
		Console console = System.console();
		if (console == null) 
		{
            System.out.println("ERROR: Could not obtain Console Instance.");
            System.exit(0);
        }
		String user = console.readLine("\nUsername: ");
		char[] pass = console.readPassword("Password: ");
		Arrays.fill(pass, '*');
		String pwd = new String(pass);
		
		if(authorization(user.toLowerCase(), pwd))
		{
			/*Access account.*/ 
		}
	}
	private static boolean authorization(String user, String pass)
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
					return autherized;
				}
				else
				{
					autherized = false;
					System.out.println("Password is invalid.");
					bufferedReader.close();
					return autherized;
				}
			}
			bufferedReader.close();
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