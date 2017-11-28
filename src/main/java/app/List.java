package app;

import Utilities.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import setup.User;

public class List implements CommandUser
{
	private String Name;
	private ArrayList<Tasks> taskList;

	public List()
	{
		taskList = new ArrayList<Tasks>();
	}

	@Override
	public boolean commandHandler() {
		Commands commands = new Commands();
		commands.addCommand(1, "Get all tasks");
		commands.addCommand(2, "View a task");
		commands.addCommand(3, "Time view of tasks");
		commands.addCommand(4, "Mark a task completed");
		commands.addCommand(5, "Create new task");
		commands.addCommand(6, "Edit a task");
		commands.addCommand(7, "Delete a task");
		commands.addCommand(8, "Edit list name");
		commands.addCommand(9, "Help");
		commands.addCommand(10, "Back To Dashboard");

		String availableCommands = commands.toString();

        boolean cont = true;
        int command;
        int commandReturn = 0;

		Stream stream = new Stream();
        stream.writeToConsole("\n(List \""+ Name +"\") "+ availableCommands);

        do{
			stream.writeToConsole("\n(List \""+ Name +"\") "); //DISPLAY PAGE NAME
			stream.writeToConsole("Press " + Integer.toString(commands.size()-1) + " to Display Available Commands.\nEnter your command: ");

            try{
                command = stream.readIntFromConsole();

                if(command > commands.size() || command == 0)
                    throw new Exception();

                commandReturn = commandCenter(command, availableCommands);
                if(commandReturn == 1)
                    cont = false;
            }
            catch (Exception e)
            {
                stream.writeToConsole("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands.\n");
            }

        }while(cont);

		return false;
	}

	/*Command Handling*/
	public int commandCenter(int command, String availableCommands)
	{
		Stream stream = new Stream();
		switch(command)
		{
			case 1: stream.writeToConsole(GetTasks()); break;
            case 2: viewTask(); break;
            case 3: timeView(); break;
            case 4: int j = indexer(); taskList.get(j).markCompleted(); break;
            case 5: createTask(); break;
            case 6: int i = indexer(); editTask(i); break;
            case 7: deleteTask(); break;
            case 8: editListName(); break;
			case 9: stream.writeToConsole(availableCommands); break;
			case 10: return 1;
		}
		return 0;
	}

	public String GetTasks()
	{
		String output = "";

		if(taskList.size() == 0)
			output = "\nNo tasks\n";
		else
			output = "\nYour tasks:\n";

		for(int i=0; i < taskList.size(); i++)
		{
			output = output + "\t" + (i+1) + " -- " + taskList.get(i).getDescription();
			if(taskList.get(i).getIsCompleted().equals(true))
			{
				output = output + " *COMPLETED* ";
			}
			output = output + "\n";
		}
		return output;
	}

	public void timeView()
    {
        Stream stream = new Stream();
        String output = "";

        if(taskList.size() == 0)
            output = "\nNo tasks\n";

        ArrayList<Tasks> todayList = new ArrayList<>();
        ArrayList<Tasks> tomorrowList = new ArrayList<>();
        ArrayList<Tasks> upcomingList = new ArrayList<>();
        ArrayList<Tasks> somedayList = new ArrayList<>();

        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date currentTime = new Date();
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentTime);

        String todayOut = "";
        String tomorrowOut = "";
        String upcomingOut = "";
        String someOut = "";

        for (int i = 0; i < taskList.size(); i++)
        {
            Date tempDate;
            Calendar tempCal = Calendar.getInstance();

            if (taskList.get(i).getNotificationDate().equals(null))
            {
                try {
                    tempDate = sdf.parse(taskList.get(i).getTimestamp());
                    if (daysBetween(tempCal, currentCal) == 0)
                    {
                        todayList.add(taskList.get(i));
                    }
                    else
                    {
                        tempCal.setTime(tempDate);

                        if (daysBetween(tempCal, currentCal) < 0)
                            todayList.add(taskList.get(i));
                        else if (daysBetween(tempCal,currentCal) == 1)
                            tomorrowList.add(taskList.get(i));
                        else if (daysBetween(tempCal,currentCal) > 1 && daysBetween(tempCal,currentCal) < 7)
                            upcomingList.add(taskList.get(i));
                        else
                            somedayList.add(taskList.get(i));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    tempDate = sdf.parse(taskList.get(i).getNotificationDate());
                    if (daysBetween(tempCal, currentCal) == 0)
                    {
                        todayList.add(taskList.get(i));
                    }
                    else
                    {
                        tempCal.setTime(tempDate);
                        if (daysBetween(tempCal, currentCal) < 0)
                            todayList.add(taskList.get(i));
                        else if (daysBetween(tempCal,currentCal) == 1)
                            tomorrowList.add(taskList.get(i));
                        else if (daysBetween(tempCal,currentCal) > 1 && daysBetween(tempCal,currentCal) < 7)
                            upcomingList.add(taskList.get(i));
                        else
                            somedayList.add(taskList.get(i));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            int count = 1;

            if (!todayList.isEmpty())
                todayOut = "\nToday: \n";
            for (int j = 0; j < todayList.size(); j++)
            {
                todayOut += "\t" + (count++) + " -- " + todayList.get(j).getDescription();
                if(todayList.get(j).getIsCompleted().equals(true))
                {
                    todayOut += " *COMPLETED* ";
                }
                else
                {
                    if (!todayList.get(j).getNotificationDate().equals(null))
                        todayOut += " " + todayList.get(j).getNotificationDate();
                }
                todayOut += "\n";
            }
            if (!tomorrowList.isEmpty())
                tomorrowOut = "\nTomorrow: \n";
            for (int k = 0; k < tomorrowList.size(); k++)
            {
                tomorrowOut += tomorrowOut + "\t" + (count++) + " -- " + tomorrowList.get(k).getDescription();
                if(tomorrowList.get(k).getIsCompleted().equals(true))
                {
                    tomorrowOut += " *COMPLETED* ";
                }
                else
                {
                    if (!tomorrowList.get(k).getNotificationDate().equals(null))
                        tomorrowOut += " " + tomorrowList.get(k).getNotificationDate();
                }
                tomorrowOut += "\n";
            }
            if (!upcomingList.isEmpty())
                upcomingOut = "\nUpcoming: \n";
            for (int l = 0; l < upcomingList.size(); l++)
            {
                upcomingOut += "\t" + (count++) + " -- " + upcomingList.get(l).getDescription();
                if(upcomingList.get(l).getIsCompleted().equals(true))
                {
                    upcomingOut += " *COMPLETED* ";
                }
                else
                {
                    if (!upcomingList.get(l).getNotificationDate().equals(null))
                        upcomingOut += " " + upcomingList.get(l).getNotificationDate();
                }
                upcomingOut += "\n\n";
            }

            if (!somedayList.isEmpty())
                someOut = "\nSomeday: \n";
            for (int m = 0; m < somedayList.size(); m++)
            {
                someOut += "\t" + (count++) + " -- " + somedayList.get(m).getDescription();
                if(somedayList.get(m).getIsCompleted().equals(true))
                {
                    someOut += " *COMPLETED* ";
                }
                else
                {
                    if (!somedayList.get(m).getNotificationDate().equals(null))
                        someOut += " " + somedayList.get(m).getNotificationDate();
                }
                someOut += "\n\n";
            }
        }
        stream.writeToConsole(todayOut + tomorrowOut + upcomingOut + someOut);
    }

	public void viewTask()
    {
        Stream stream = new Stream();
        stream.writeToConsole(GetTasks());
        int taskNum;
        do
        {
            stream.writeToConsole("\nEnter task number: ");
            taskNum = stream.readIntFromConsole();
            if(taskNum > 0 || taskNum < taskList.size())
            {
                taskList.get(taskNum-1).printTask();
            }
            else
                stream.writeToConsole("Invalid index!\n");
        } while (taskNum < 0 || taskNum > taskList.size());

    }

	public List(String name)
	{
		Name = name;
		taskList = new ArrayList<Tasks>();
	}

	public String getName()
	{
		return Name;
	}

	private void createTask()
	{
		Tasks task = new Tasks();
		task.addDescription();
		task.setTimestamp();

		Stream stream = new Stream();
		String ans;
		stream.writeToConsole("\nWould you like to set a date for this task? (Y/N)\n");
		ans = stream.readLineFromConsole().toUpperCase();
		if(ans.equals("Y")) //add date
		{
			task.addDate();

		}
		stream.writeToConsole("\nWould you like to add a note? (Y/N)\n");
		ans = stream.readLineFromConsole().toUpperCase();
		if (ans.equals("Y"))
		{
			task.addNote();
		}
		stream.writeToConsole("\nWould you like to add a subtask? (Y/N)\n");
		ans = stream.readLineFromConsole().toUpperCase();
		if (ans.equals("Y"))
		{
            boolean addMore;
			do
			{
				task.addSubtask();
				stream.writeToConsole("Would you like to add another subtask? (Y/N)\n");
				ans = stream.readLineFromConsole().toUpperCase();
				if(ans.equals("Y"))
					addMore = true;
				else
					addMore = false;
			} while(addMore);
		}
		if(isCorporateUser()) {
			stream.writeToConsole("\nIs this task repeated? (Y/N)\n");
			ans = stream.readLineFromConsole().toUpperCase();
			if (ans.equals("Y")) {
				task.flipRepeated();
			}
		}
		taskList.add(task);

		stream.writeToConsole("\nTask has been created!\n");
	}
	private void deleteTask()
	{
		Stream stream = new Stream();
	    stream.writeToConsole("Which task do you want to delete?\n" + GetTasks() + "\n");
	    stream.writeToConsole("Enter task number: ");

        stream = new Stream();
	    int taskPos = stream.readIntFromConsole();

		stream.writeToConsole("\nAre you sure you want to delete this task? (Y/N)\n");
		String ans = stream.readLineFromConsole().toUpperCase();
		if(ans.equals("Y"))
		{
			//removes index-1 since the task will be printed starting with 1
            stream.writeToConsole("Task has been deleted!");
			taskList.remove(taskPos-1);
		}
		else
		    stream.writeToConsole("Task will not be deleted!");
	}
	private int indexer()
    {
        Stream stream = new Stream();
        int j = 1;
        if (taskList.isEmpty())
        {
            stream.writeToConsole("No tasks!");
            return -1;
        }
        else
        {
            stream.writeToConsole("Your tasks: ");
        }
        for (int i = 0; i < taskList.size(); i++)
        {
            stream.writeToConsole("\n\t"+(j++) + ": " + taskList.get(i).getDescription());
        }
        int returnInteger;
        do
        {
            stream.writeToConsole("\nEnter task number: ");
            returnInteger = stream.readIntFromConsole();
        } while(returnInteger < 0);
        return returnInteger-1;
    }
	private void editTask(int index)
	{
		Stream stream = new Stream();
		taskList.get(index).taskHandler();
	}
    private void editListName()
    {
        Stream stream = new Stream();
        stream.writeToConsole("\nCurrent list name: " + Name);
        do
        {
            stream.writeToConsole("\nEnter the new list name: ");
            String newName = stream.readLineFromConsole();
            if (Name.equals(null) || Name.equals(""))
                stream.writeToConsole("\nInvalid input for list name!");
            else
            {
                stream.writeToConsole("Confirm (Y/N): " + newName);
                String ans = stream.readLineFromConsole().toUpperCase();
                if(ans.equals("Y"))
                {
                    Name = newName;
                    stream.writeToConsole("\nThe new list is now named: " + Name + " \n");
                }
                else
                {
                    stream.writeToConsole("\nCancelled!\n");
                }
            }
        } while(Name.equals(null) || Name.equals(""));
    }
	private boolean isCorporateUser(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + "LastLogin.txt"));
			User user = Account.getUserInfo(reader.readLine());
			reader.close();

			if(user.getCorporate().equals("1"))
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
    public static int daysBetween(Calendar day1, Calendar day2){
        Calendar dayOne = (Calendar) day1.clone(),
                dayTwo = (Calendar) day2.clone();

        if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
            return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
        } else {
            if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = dayOne;
                dayOne = dayTwo;
                dayTwo = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

            while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
                dayOne.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
        }
    }
}