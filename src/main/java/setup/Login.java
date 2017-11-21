package setup;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Login
{
	public static User access()
	{
		//Console console = System.console();
		Scanner s= new Scanner(System.in);
		//if (console == null)
		//{
		//  System.out.println("ERROR: Could not obtain Console Instance.");
		//System.exit(0);
		//}
		//String user = console.readLine("\nUsername: ");
		//char[] pass = console.readPassword("Password: ");
		//Arrays.fill(pass, '*');
		//String pwd = new String(pass);
		System.out.print("\nUsername: ");
		String user = s.nextLine();
		System.out.print("Password: ");
		String pwd = s.nextLine();
		if(authorization(user.toLowerCase(), pwd))
		{
			System.out.println("Login successful\n");
			return getUserInfo(user);
		}
		else {
			System.out.println("Login failed\n");
			return null;
		}
	}
	private static boolean authorization(String user, String pass)
	{
		String line = null;
		String fileName = "Accounts/" + user + "/accountInfo.txt";
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

	public static User getUserInfo(String userEmail)
	{
		User user = null;

		try(BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + userEmail + "/accountInfo.txt")))
		{
			user = new User(userEmail, fileReader.readLine(), fileReader.readLine(), fileReader.readLine(), fileReader.readLine(), fileReader.readLine(), fileReader.readLine());
		}
		catch(Exception e)
		{

		}

		return user;
	}
}