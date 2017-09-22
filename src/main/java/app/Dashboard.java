package main.java.app;

import java.util.ArrayList;

public class Dashboard
{
    private String userFileName;
    private String userName;

    private String[] commands = {"get lists"};

    private ArrayList<List> lists;

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
