package dashboard;

import static java.lang.System.*;

public class Dashboard
{
    private String userFileName;
    private String userName;

    private String[] commands = {"", "", "", "", "", ""};

    private List[] lists;

    public Dashboard()
    {
        this("default.txt");
    }

    public Dashboard(String fileName)
    {
        userFileName = fileName;
    }

    private String commandHandler()
    {

        while(true)
        {
            return "";
        }

        //return "";
    }

    private boolean GetUserData()
    {
        //implement later when we have JSONs ready
        return false;
    }


    //handles creation of new lists, returns boolean on whether it was able to create the list
    private boolean createNewList(String listName)
    {
        return false;
    }


    //handles fetching of all list names that the user has
    private String GetLists()
    {
        String output = "";

        if(lists.length == 0)
            output = "No lists";

        for(int i=0; i<lists.length; i++)
        {
            output = output + "\n---" + lists[i].getName();
        }

        return output;
    }
}
