package app;

import java.awt.event.KeyListener;
import java.util.*;
import static java.lang.System.*;

public class Dashboard
{
    private String userFileName;
    private String userName;

    private Map<Integer, String> commands = new HashMap<Integer, String>();

    private ArrayList<List> lists;

    public Dashboard()
    {
        this("default.txt");
    }

    public Dashboard(String fileName)
    {
        userFileName = fileName;
        lists = new ArrayList<List>();

        commands.put(1, "get all lists");
        commands.put(2, "create new list");
        commands.put(3, "help");
        commands.put(4, "quit");

        commandHandler();
    }

    private void commandHandler()
    {
        boolean cont = true;
        int command = 0;
        Scanner input;

        printCommands();
        while(cont)
        {
            out.println("\nEnter your command: ");
            input = new Scanner(in);

            try{
                command = input.nextInt();

                if(command > commands.size())
                    throw new Exception();

                commandControlCenter(command);
            }
            catch (Exception e)
            {
                out.println("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands");
            }
        }

        System.exit(0);
    }

    private void commandControlCenter(int command)
    {
        switch(command)
        {
            case 1: GetLists(); break;
            case 2: out.println("Command recognized\n\n"); break;
            case 3: printCommands(); break;
            case 4: exit(0); break;

        }
    }

    protected void printCommands()
    {
        Set set = commands.entrySet();
        Iterator iterate = set.iterator();

        out.println("List of commands available:\n");
        while(iterate.hasNext())
        {
            Map.Entry i = (Map.Entry) iterate.next();
            out.println(i.getKey() + " - " + i.getValue());
        }
    }



    private boolean GetUserData()
    {
        //implement later when we have JSONs ready
        return false;
    }


    //handles creation of new lists, returns boolean on whether it was able to create the list
    private boolean createNewList(String listName)
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


    //handles fetching of all list names that the user has
    private String GetLists()
    {
        String output = "";

        if(lists.size() == 0)
            output = "No lists";

        for(int i=0; i<lists.size(); i++)
        {
            output = output + "\n---" + lists.get(i).getName();
        }

        return output;
    }
}
