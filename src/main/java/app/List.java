package app;

import Utilities.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import javafx.concurrent.Task;

public class List implements CommandUser
{
	private Commands commands;
	private Stream stream;
	private String Name;
	private ArrayList<Tasks> taskList;
	private ObjectMapper mapper;
	public List()
	{
//		commands.addCommand(1, "Get all tasks");
//		commands.addCommand(2, "Create new task");
//		commands.addCommand(3, "Edit a task");
//		commands.addCommand(4, "Delete a task");
//		commands.addCommand(5, "help");
//		commands.addCommand(6, "quit");
//
		stream = new Stream();
        taskList = new ArrayList<Tasks>();
//		stream.writeToConsole("(List) "+commands.toString());
	}

	@Override
	public boolean commandHandler() {
		return false;
	}

	/*Command Handling*/
	public int commandCenter(int command)
	{
		switch(command)
		{
			case 1: stream.writeToConsole(GetTasks()); break;
//            case 2: createTask(); break;
//            case 3: editTask(); break;
//            case 4: deleteTask(); break;
			case 5: stream.writeToConsole(commands.toString()); break;
			case 6: return 1;
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
		stream = new Stream();
	}

	public String getName()
	{
		return Name;
	}

	public void createTask(String userEmail)
	{
		Tasks task = new Tasks();
		task.addDescription();
		task.setTimestamp();

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
				ans = stream.readLineFromConsole();
				if(ans.equals("Y"))
					addMore = false;
				else
					addMore = true;
			} while(!addMore);
		}
		stream.writeToConsole("\nIs this task repeated? (Y/N)\n");
		ans = stream.readLineFromConsole().toUpperCase();
		if (ans.equals("Y"))
		{
			task.flipRepeated();
		}
		taskList.add(task);


		/*Serialize to Json*/
		mapper = new ObjectMapper();
		Gson gson = new Gson();
		String json = gson.toJson(task);
		try
		{
			mapper.writerWithDefaultPrettyPrinter()
					.writeValue(new File("Accounts/" + userEmail + "/data.json"), json);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		stream.writeToConsole("\nTask has been created!\n");
	}
	protected void deleteTask(int index)
	{
		stream.writeToConsole("\nAre you sure you want to delete this task? (Y/N)\n");
		String ans = stream.readLineFromConsole();
		if(ans.toUpperCase() == "Y")
		{
			//removes index-1 since the task will be printed starting with 1
			taskList.remove(index-1);
		}
	}
	protected void editTask(int index)
	{
		//command list to edit description, date, note, or subtasks;
	}
}