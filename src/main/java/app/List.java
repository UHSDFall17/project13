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
    
	private void createTask()
	{
		Tasks t = new Tasks();
		t.addDescription();
		t.setTimestamp();

		String ans;
		System.out.println("\nWould you like to set a date for this task? (Y/N)");
		ans = scanner.nextLine().toUpperCase();
		if(ans.equals("Y"))
		{
			t.addDate();
		}
		taskList.add(t);
		System.out.println("\nTask has been created!");
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
}
