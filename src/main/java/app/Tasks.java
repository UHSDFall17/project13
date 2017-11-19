package app;

import Utilities.*;

import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Tasks
{
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:SS");
    private String description = null;
    private String notification = null; //date due for task
    private String timeStamp = null; //time created for task
    private boolean isRepeated = false;
    private boolean isCompleted = false;
    private Stack<String> subtasks = null;
    private String note = null;
    private Stream stream;

    public Tasks()
    {
        stream =  new Stream();
        subtasks = new Stack<String>();
    }
    /*Task creation helper*/
    protected void addDescription()
    {
        do
        {
            stream.writeToConsole("Enter the task description: \n");
            description = stream.readLineFromConsole();
            if (description.equals(null) || description.equals(""))
                stream.writeToConsole("Not an appropriate description.\nPlease try again!\n\n\n");
        }while(description == null || description == "");
    }
    protected void addDate()
    {
        int year, month, day;

        do
        {
            stream.writeToConsole("\nYear number: \n");
            year = stream.readIntFromConsole();
        }while(!isValidYear(year));

        do
        {
            stream.writeToConsole("Month number: \n");
            month = stream.readIntFromConsole();
        }while(!isValidMonth(month));

        do
        {
            stream.writeToConsole("Day number: \n");
            day = stream.readIntFromConsole();
        }while(!isValidDay(year,month,day));

        stream.readLineFromConsole();
        stream.writeToConsole("Do you wish to enter a time for completion? (Y/N)\n");
        String ans = stream.readLineFromConsole().toUpperCase();
        if(ans.equals("Y"))
        {
            int hour, min;
            do
            {
                stream.writeToConsole("Enter hour (0-23): ");
                hour = stream.readIntFromConsole();
                stream.writeToConsole("Enter minute (0-59): ");
                min = stream.readIntFromConsole();
            }while(!isValidTime(hour,min));

            setDate(year, month, day, hour, min);
        }
        else
        {
            setDate(year, month, day);
        }
    }
    protected void addNote()
    {
        do
        {
            stream.readLineFromConsole();
            stream.writeToConsole("Enter the note for the task: \n");
            note = stream.readLineFromConsole();
            if (description.equals(null) || description.equals(""))
                stream.writeToConsole("Not an appropriate note.\nPlease try again!\n\n\n");
        }while(description == null || description == "");
    }
    protected void addSubtask()
    {
        stream.writeToConsole("Enter the subtask description: \n");
        String st = stream.readLineFromConsole();
        if (st == null || st.equals(""))
            stream.writeToConsole("Subtask has no description.\n");
        else
        {
            subtasks.push(st);
        }
    }
    protected void printSubtask()
    {
        stream.writeToConsole("Subtasks:\n");
        if(subtasks == null)
            return;
        else
        {
            Stack<String> stCopy = new Stack();
            stCopy.addAll(subtasks);
            for(int i = 0; i < subtasks.size(); i++)
            {
                String temp = stCopy.pop();
                stream.writeToConsole("         "+ temp + "\n"); //To whom it may concern: You can use "/t" if you want the String indented. -Stacy
            }
        }
    }
    protected void flipRepeated() //flip false to true or true to false
    {
        if(isRepeated == false)
            isRepeated = true;
        else
            isRepeated = false;
    }

    protected void deleteSubtask()
    {

    }
    protected String getDescription()
    {
        return description;
    }

    private void setDate(int y, int m, int d, int hr, int min) //date has assigned hh:mm
    {
        Calendar taskCal = Calendar.getInstance();
        taskCal.set(y, m, d, hr, min);
        Date taskDate = taskCal.getTime();
        notification = sdf.format(taskDate);
    }
    private void setDate(int y, int m, int d) //date is a reminder for a day w/o hh:mm
    {
        Calendar taskCal = Calendar.getInstance();
        taskCal.set(y, m, d);
        Date taskDate = taskCal.getTime();
        notification = sdf.format(taskDate);
    }
    protected String getNotificationDate()
    {
        return notification;
    }

    protected void setTimestamp() //creation date for sorting purposes
    {
        timeStamp = sdf.format(currentTimeStamp());
    }
    private Timestamp currentTimeStamp()
    {
        Date current = new Date();
        return new Timestamp(current.getTime());
    }
    protected String getTimestamp()
    {
        return timeStamp;
    }

    protected boolean markCompleted()
    {
        if(!isCompleted)
            return isCompleted = true;
        else
        {
            stream.writeToConsole("\nTask has already been mark completed.\n");
            stream.writeToConsole("Would you like to mark it as incomplete? (Y/N)\n");
            //Console input
            String temp = (stream.readLineFromConsole().toUpperCase());
            if(temp.equals(""))
            {
                stream.writeToConsole("Please enter Y or N.\n");
                markCompleted();
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
            stream.writeToConsole("\nPlease enter a valid year integer.\n\n");
            return false;
        }

    }
    private boolean isValidMonth(int m)
    {
        if (m >= 1 && m <= 12)
            return true;
        else
        {
            stream.writeToConsole("\nPlease enter a valid month integer.\n\n");
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
            stream.writeToConsole("\nHour is not within range (0-23).\n");
            return false;
        }
        if(!(m >= 0 && m <=59))
        {
            stream.writeToConsole("\nMinutes are noth within range (0-59).\n");
            return false;
        }
        return true;
    }
}
