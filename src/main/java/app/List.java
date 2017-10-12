package app;

import java.util.*;

public class List
{
    Scanner scanner = new Scanner(System.in);
    private String Name;
    ArrayList<Tasks> taskList;

    public List(String name)
    {
        Name = name;
    }

    public String getName()
    {
        return Name;
    }
    
        private void createTask(final Tasks t, final String taskString)
	{
		if(taskString == null)
		{
			System.out.println("No task description entered.");
		}
		else
		{
			t.description = taskString;
			t.setTimestamp();
			
			String ans;
			System.out.println("\nWould you like to set a date for this task? (Y/N)");
			ans = scanner.nextLine().toUpperCase();
			if(ans == "Y")
			{
				int year, month, day;
				
				do
				{
					System.out.println("\nYear number: ");
					year = scanner.nextInt();
				}while(!isValidYear(year));
				
				do
				{
					System.out.println("Month number: ");
					month = scanner.nextInt();
				}while(!isValidMonth(month));
				
				do
				{
					System.out.println("Day number: ");
					day = scanner.nextInt();
				}while(!isValidDay(year,month,day));
				
				System.out.print("Do you wish to enter a time for completion? (Y/N)");
				ans = scanner.nextLine().toUpperCase();
				if(ans == "Y")
				{
					int hour, min;
					do
					{
						ArrayList<String> temp = new ArrayList<String>();
						System.out.println("Enter hour (0-23): ");
						hour = scanner.nextInt();
						System.out.println("Enter minute (0-59):");
						min = scanner.nextInt();
					}while(!isValidTime(hour,min));
					
					t.setDate(year, month, day, hour, min);
				}
				else
				{
					t.setDate(year, month, day);
				}
			}
			taskList.add(t);
			System.out.println("\nTask has been created!");
		}
	}
	private void deleteTask(int index)
	{
		System.out.println("\nAre you sure you want to delete this task? (Y/N)");
		String ans = scanner.nextLine();
		if(ans.toUpperCase() == "Y")
		{
			taskList.remove(index);
		}
		else
		{
			return;
		}
	}
	private void editTask(int index)
	{
		//command list to edit description or date or time
	}
	
	/*Date Validation*/
	private boolean isValidYear(int y)
	{
		if(y >= 1990)
			return true;
		else
		{
			System.out.println("\nPlease enter a valid year integer.\n");
			return false;
		}

	}
	private boolean isValidMonth(int m)
	{
		if (m >= 1 && m <= 12)
			return true;
		else 
		{
			System.out.println("\nPlease enter a valid month integer.\n");
			return false;
		}
	}
	private boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return (y % 4 == 0);
    }
	private boolean isValidDay(int y, int m, int d)
	{
		int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false; //between 1 and days[month]
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
	}
	private boolean isValidTime(int h, int m)
	{
		if(!(h >= 0 && h <= 23))
		{
			System.out.println("\nHour is not within range (0-23).");
			return false;
		}
		if(!(m >= 0 && m <=59))
		{
			System.out.println("\nMinutes are noth within range (0-59).");
			return false;
		}
		return true;
			
	}
}
