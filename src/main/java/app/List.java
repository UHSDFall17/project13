package app;

import Utilities.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class List implements CommandUser
{
//	private Commands commands;
//	private Stream stream;
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
		commands.addCommand(2, "Create new task");
		commands.addCommand(3, "Edit a task");
		commands.addCommand(4, "Delete a task");
		commands.addCommand(5, "Edit list name");
		commands.addCommand(6, "Help");
		commands.addCommand(7, "Back To Dashboard");

		String availableCommands = commands.toString();

        boolean cont = true;
        int command;
        int commandReturn = 0;

		Stream stream = new Stream();
        stream.writeToConsole("(List \""+ Name +"\") "+ availableCommands);

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
            case 2: createTask(); break;
            case 3: int i = indexer(); editTask(i); break;
            case 4: deleteTask(); break;
            case 5: break;
			case 6: stream.writeToConsole(availableCommands); break;
			case 7: return 1;
		}
		return 0;
	}

	public String GetTasks()
	{
		String output = "";

		if(taskList.size() == 0)
			output = "\nNo tasks";
		else
			output = "\nYour tasks:\n";

		for(int i=0; i < taskList.size(); i++)
		{
			output = output + "\n-- " + taskList.get(i).getDescription();
		}
		return output;
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
		stream.writeToConsole("\nIs this task repeated? (Y/N)\n");
		ans = stream.readLineFromConsole().toUpperCase();
		if (ans.equals("Y"))
		{
			task.flipRepeated();
		}
		taskList.add(task);

		stream.writeToConsole("\nTask has been created!\n");
	}
	private void deleteTask()
	{
		Stream stream = new Stream();
	    stream.writeToConsole("Which task do you want to delete?\n" + GetTasks() + "\n\n");
	    stream.writeToConsole("Enter task number: ");

	    int taskPos = stream.readIntFromConsole();

		stream.writeToConsole("\nAre you sure you want to delete this task? (Y/N)\n");
		String ans = stream.readLineFromConsole();
		if(ans.toUpperCase() == "Y")
		{
			//removes index-1 since the task will be printed starting with 1
			taskList.remove(taskPos-1);
		}
	}
	private int indexer()
    {
        Stream stream = new Stream();
        int j = 1;
        for (int i = 0; i < taskList.size(); i++)
        {
            stream.writeToConsole((j++) + ": " + taskList.get(i).getDescription());
        }
        int returnInteger;
        do
        {
            stream.writeToConsole("Enter the index of the task: ");
            returnInteger = stream.readIntFromConsole();
        } while(returnInteger > 0);
        return returnInteger-1;
    }
	private void editTask(int index)
	{
		Stream stream = new Stream();
		taskList.get(index).taskHandler();
	}
}