package app;

import Utilities.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import setup.DataStorageGSON;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import static java.lang.System.*;

public class Dashboard implements CommandUser
{
    private String jsonFile;
    private String userName;
    private Commands commands;
    private Stream stream;
    private Gson gson;


    private ArrayList<List> lists = null;

    public Dashboard()
    {
        this("default.txt");
//        lists = new ArrayList<List>();
    }

    public Dashboard(String fileName)
    {
        jsonFile = "Accounts/" + fileName + "/data.json";
        lists = new ArrayList<List>();


        DataStorageGSON dataStorageGSON = new DataStorageGSON(jsonFile);
        ArrayList<String> listNames  = dataStorageGSON.getJsonLists();
        for(String e : listNames)
            storeNewList(e);

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
            stream.writeToConsole("\n(Dashboard) Enter your command: ");

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
                stream.writeToConsole("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands.\n");
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
            case 1: stream.writeToConsole(displayLists() + "\n"); break;
            case 2: createNewList(); break;
            case 3: editList();break;
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

            gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter(jsonFile)) {
                gson.toJson(newList, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }

    private void createNewList()
    {
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
            output = "\nYour lists:\n";

        for(int i=0; i<lists.size(); i++)
        {
            output = output + "\n" + (i+1) + " -- " + lists.get(i).getName();
        }

        return output;
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    public void editList()
    {
        stream.writeToConsole("Which list?\n");
        stream.writeToConsole(displayLists() + "\n\nEnter list number: ");

        int listNum = stream.readIntFromConsole();

        lists.get(listNum-1).commandCenter(1);

    }
}
