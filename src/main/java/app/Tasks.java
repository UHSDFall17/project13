package main.java.app;

import java.io.Console;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Tasks 
{
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:SS");
	protected String description = null;
	private String date = null; //date due for task
	private String timeStamp = null; //time created for task
	private boolean isRepeated = false;
	private boolean isCompleted = false;
	//private ArrayList<subTasks> subTasks = null;
	
	
	protected String getDescription()
	{
		return description;
	}
	
	protected void setDate(int y, int m, int d, int hr, int min) //date has assigned hh:mm
	{
		Calendar taskCal = Calendar.getInstance();
		taskCal.set(y, m, d, hr, min);
		Date taskDate = taskCal.getTime(); 
		date = sdf.format(taskDate).toString();
	}
	protected void setDate(int y, int m, int d) //date is a reminder for a day w/o hh:mm
	{
		Calendar taskCal = Calendar.getInstance();
		taskCal.set(y, m, d);
		Date taskDate = taskCal.getTime(); 
		date = sdf.format(taskDate).toString();
	}	
	protected String getDate()
	{
		return date;
	}
	
	protected void setTimestamp() //creation date for sorting purposes
	{
		timeStamp = sdf.format(currentTimeStamp()).toString();
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
}
