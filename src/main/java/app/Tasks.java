package app;

import java.io.Console;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Stack;
import java.util.Scanner;

public class Tasks 
{
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:SS");
	protected String description = null;
	private String date = null; //date due for task
	private String timeStamp = null; //time created for task
	private boolean isRepeated = false;
	private boolean isCompleted = false;
	protected Stack<String> subtasks= null;
	protected String note = null;
	Scanner scanner = new Scanner(System.in);

	protected void addDescription()
    {
        do
        {
            System.out.println("Enter the task description: ");
            description = scanner.nextLine();
            if (description.equals(null) || description.equals(""))
                System.out.println("Not an appropriate description.\nPlease try again!\n\n");
        }while(description != null || description !=  "");
    }
	protected void addDate()
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
        String ans = scanner.nextLine().toUpperCase();
        if(ans.equals("Y"))
        {
            int hour, min;
            do
            {
                System.out.println("Enter hour (0-23): ");
                hour = scanner.nextInt();
                System.out.println("Enter minute (0-59):");
                min = scanner.nextInt();
            }while(!isValidTime(hour,min));

            setDate(year, month, day, hour, min);
        }
        else
        {
            setDate(year, month, day);
        }
    }
	protected void addSubsask(String st)
	{
		if (st == null || st.equals(""))
			System.out.println("Subtask has no description.");
		else
			subtasks.push(st);
	}
	protected void printSubtask()
	{
		System.out.println("Subtasks:");
		if(subtasks == null)
			return;
		else
		{
			for(int i = 0; i < subtasks.size(); i++)
			{
				String temp = subtasks.pop();
				System.out.println("         "+ temp);
			}
		}
	}
	protected String getDescription()
	{
		return description;
	}
	
	protected void setDate(int y, int m, int d, int hr, int min) //date has assigned hh:mm
	{
		Calendar taskCal = Calendar.getInstance();
		taskCal.set(y, m, d, hr, min);
		Date taskDate = taskCal.getTime();
		date = sdf.format(taskDate);
	}
	protected void setDate(int y, int m, int d) //date is a reminder for a day w/o hh:mm
	{
		Calendar taskCal = Calendar.getInstance();
		taskCal.set(y, m, d);
		Date taskDate = taskCal.getTime(); 
		date = sdf.format(taskDate);
	}	
	protected String getDate()
	{
		return date;
	}
	
	protected void setTimestamp() //creation date for sorting purposes
	{
		timeStamp = sdf.format(currentTimeStamp());
	}
	protected Timestamp currentTimeStamp() 
	{
		Date current = new Date();
		return new Timestamp(current.getTime());
	}
	protected String getTimestamp()
	{
		return timeStamp;
	}
	
	protected boolean markCompleted(Console console)
	{
		if(!isCompleted)
			return isCompleted = true;
		else
		{
			System.out.println("\nTask has already been mark completed.");
			System.out.println("Would you like to mark it as incomplete? (Y/N)");
			//Console input
			String temp = (console.readLine()).toUpperCase();
			 if(temp.equals(""))
			 {
				 System.out.println("Please enter Y or N.");
		         markCompleted(console);
		     }
		     else if(temp.equals("Y"))
		     {
		    	 return isCompleted = false;
		     }
		     else if(temp.equals("N"))
		     {
		    	 return isCompleted;
		     }
		}
		return isCompleted;
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
