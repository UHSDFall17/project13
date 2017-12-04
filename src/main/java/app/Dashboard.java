package app;

import Utilities.*;

import java.io.IOException;
import java.util.*;

public class Dashboard implements CommandUser
{
    private ArrayList<List> lists = null;

    public Dashboard()
    {
        lists = new ArrayList<List>();
    }

    public boolean commandHandler()
    {
        Commands commands = new Commands();

        commands.addCommand(1, "Get all lists");
        commands.addCommand(2, "Open a list");
        commands.addCommand(3, "Create new list");
        commands.addCommand(4, "Edit list");
        commands.addCommand(5, "Delete list");
        commands.addCommand(6, "Settings/ Preferences");
        commands.addCommand(7, "Logout");
        commands.addCommand(8, "Help");
        commands.addCommand(9, "Quit");

        String availableCommands = commands.toString();

        boolean cont = true;
        int command;
        int commandReturn = 0;

        Stream stream = new Stream();
        stream.writeToConsole("\n(Dashboard) "+ availableCommands);
        do
        {
            stream.writeToConsole("\n(Dashboard) "); //DISPLAY PAGE NAME
            stream.writeToConsole("Press \"" + (commands.size() - 1) + "\" to Display Available Commands.\nEnter your command: ");

            try{
                command = stream.readIntFromConsole();

                if(command > commands.size() || command == 0)
                    throw new Exception();

                commandReturn = commandCenter(command, availableCommands);
                if(commandReturn == 1 || commandReturn == 2)
                    cont = false;
            }
            catch (Exception e)
            {
                stream.writeToConsole("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands.\n");
            }
        }while(cont);

        if(commandReturn == 2) //logout
            return true;
        else
            return false;  //quit without logging out
    }

    public int commandCenter(int command, String availableCommands) throws IOException {
        Stream stream = new Stream();

        switch(command)
        {
            case 1: stream.writeToConsole(displayLists() + "\n"); break; //display all lists
            case 2: openList(); break;
            case 3: createNewList(); break;
            case 4: editList();break;
            case 5: deleteList(); break;
            case 6: Preference preference = new Preference(); preference.commandHandler(); break; //settings/ preferences
            case 7: stream=null; return 2; //logout
            case 8: stream.writeToConsole("\n(Dashboard) " + availableCommands); break; //display available commands
            case 9: return 1; //quit
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
        Stream stream = new Stream();

        stream.writeToConsole("Enter new list name:\n");
        String listName = stream.readLineFromConsole();

        boolean stored = storeNewList(listName);

        if(stored)
            stream.writeToConsole("Saved successfully!\n");
        else {
            boolean loop = true;
            stream.writeToConsole("Could not save new list. ");
            while(loop)
            {
                stream.writeToConsole("Try again?(Y/N)\n");
                String ans = stream.readLineFromConsole().toLowerCase();
                if (ans.compareTo("y") == 0)
                {
                    loop = false;
                    createNewList();
                }
                else if (ans.compareTo("n") > 0 || ans.compareTo("n") < 0)
                {
                    stream.writeToConsole("Invalid option. \n");
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
            output = "\nYour lists:";

        for(int i=0; i<lists.size(); i++)
        {
            output = output + "\n\t" + (i+1) + " -- " + lists.get(i).getName();
        }

        return output;
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    public void editList()
    {
        Stream stream = new Stream();

        stream.writeToConsole("\nWhich list do you want to Edit?\n");
        stream.writeToConsole(displayLists() + "\n\nEnter list number: ");

        int listNum = stream.readIntFromConsole();

        lists.get(listNum-1).commandCenter(1, "");

    }

    public void openList()
    {
        Stream stream = new Stream();

        stream.writeToConsole("\nWhich list do you want to Open?\n");
        stream.writeToConsole(displayLists() + "\n\nEnter list number: ");

        int listNum = stream.readIntFromConsole();

        lists.get(listNum-1).commandHandler();
    }

    public void deleteList()
    {
        Stream stream = new Stream();

        stream.writeToConsole("Which list do you want to Delete?\n" + displayLists() + "\n\n");
        stream.writeToConsole("Enter list number: ");

        int listNum = stream.readIntFromConsole();

        stream.writeToConsole("Are you sure you want to delete " + lists.get(listNum-1).getName() + "(Y/N)?\n");

        String doubt = stream.readLineFromConsole().toLowerCase();

        if(doubt.compareTo("y") == 0) {
            lists.remove(listNum - 1);
            stream.writeToConsole("Deletion successful!\n");
        }
        else if(doubt.compareTo("n") == 0)
        {
            stream.writeToConsole("Deletion cancelled...\n");
        }
        else
        {
            stream.writeToConsole("Not a valid option.\n");
        }
    }
}
