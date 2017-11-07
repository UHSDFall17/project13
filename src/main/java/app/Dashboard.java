package app;

import Utilities.*;

import java.util.*;
import static java.lang.System.*;

public class Dashboard implements CommandUser
{
    private String userFileName;
    private String userName;
    private Commands commands;
    private Stream stream;

    private ArrayList<List> lists;

    public Dashboard()
    {
        this("default.txt");
    }

    public Dashboard(String fileName)
    {
        userFileName = fileName;
        lists = new ArrayList<List>();

        /*SET DEFAULT LISTS*/
        storeNewList("Personal");
        storeNewList("Work");
        storeNewList("Grocery List");

        commands = new Commands();

        commands.addCommand(1, "Get all lists");
        commands.addCommand(2, "Create new list");
        commands.addCommand(3, "Edit lists");
        commands.addCommand(4, "logout");
        commands.addCommand(5, "help");
        commands.addCommand(6, "quit");

        stream = new Stream();

        stream.writeToConsole("(Dashboard) "+commands.toString());
    }

    public boolean commandHandler()
    {
        boolean cont = true;
        int command;
        int commandReturn = 0;

        do
        {
            out.print("\n(Dashboard) Enter your command: ");
            //input = new Scanner(in);

            try{
                command = stream.readIntFromConsole();

                if(command > commands.size())
                    throw new Exception();

                commandReturn = commandCenter(command);
                if(commandReturn == 1 || commandReturn == 2)
                    cont = false;
            }
            catch (Exception e)
            {
                out.println("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands");
            }
        }while(cont);

        if(commandReturn == 2) //logout
            return true;
        else
            return false;  //quit without logging out
    }

    public int commandCenter(int command)
    {
        switch(command)
        {
            case 1: out.println(displayLists()); break;
            case 2: createNewList(); break;
            case 3: break;
            case 4: stream=null; return 2;
            case 5: stream.writeToConsole(commands.toString()); break;
            case 6: return 1;
        }

        return 0;
    }

    private boolean GetUserData()
    {
        //implement later when we have JSONs ready
        return false;
    }


    //handles creation of new lists, returns boolean on whether it was able to create the list
    public boolean storeNewList(String listName)
    {
        List newList;

        if(listName.length() >= 1)
        {
            newList = new List(listName);
            lists.add(newList);
            return true;
        }

        return false;
    }

    private void createNewList()
    {
        Scanner input = new Scanner(in);
        out.print("Enter new list name: ");
        String listName = input.nextLine();

        boolean stored = storeNewList(listName);

        if(stored)
            out.println("Saved successfully!");
        else {
            boolean loop = true;
            out.print("Could not save new list. ");
            while(loop)
            {
                out.println("Try again?(Y/N)");
                String ans = input.nextLine();
                ans = ans.toLowerCase();
                if (ans.compareTo("y") == 0)
                {
                    loop = false;
                    createNewList();
                }
                else if (ans.compareTo("n") > 0 || ans.compareTo("n") < 0)
                {
                    out.println("Invalid option. ");
                }
                else
                    loop = false;
            }
        }
    }


    //handles fetching of all list names that the user has
    public String displayLists()
    {
        String output = "";

        if(lists.size() == 0)
            output = "\nNo lists";
        else
            output = "\nYour lists:\n";

        for(int i=0; i<lists.size(); i++)
        {
            output = output + "\n-- " + lists.get(i).getListName();
        }

        return output;
    }

    public ArrayList<List> getLists() {
        return lists;
    }
}
