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
        this("../../data/default.txt");
    }

    public Dashboard(String fileName)
    {
        userFileName = fileName;
        lists = new ArrayList<List>();

        commands.put(1, "Get all lists");
        commands.put(2, "Create new list");
        commands.put(3, "Edit lists");
        commands.put(4, "logout");
        commands.put(5, "help");
        commands.put(6, "quit");

        //commandHandler();
    }

    public void commandHandler()
    {
        boolean cont = true;
        int command = 0;
        Scanner input;

        printCommands();
        while(cont)
        {
            out.print("\nEnter your command: ");
            input = new Scanner(in);

            try{
                command = input.nextInt();

                if(command > commands.size())
                    throw new Exception();

                cont = commandControlCenter(command);
            }
            catch (Exception e)
            {
                out.println("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands");
            }
        }

    }

    private boolean commandControlCenter(int command)
    {
        switch(command)
        {
            case 1: out.println(GetLists()); break;
            case 2: createNewList(); break;
            case 3: break;
            case 4: return false;
            case 5: printCommands(); break;
            case 6: exit(0); break;

        }

        return true;
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
    private boolean storeNewList(String listName)
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
    private String GetLists()
    {
        String output = "";

        if(lists.size() == 0)
            output = "\nNo lists";
        else
            output = "\nYour lists:\n";

        for(int i=0; i<lists.size(); i++)
        {
            output = output + "\n-- " + lists.get(i).getName();
        }

        return output;
    }
}
