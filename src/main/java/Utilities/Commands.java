package Utilities;

import java.util.*;

public class Commands {

    private Map<Integer, String> commands;

    public Commands()
    {
        this(new String[0]);
    }

    public Commands(String[] comms)
    {
        commands = new HashMap<Integer, String>();

        for(int i=1; i <= comms.length; i++) {
            commands.put(i, comms[i-1]);
        }
    }

    public void addCommand(int key, String value)
    {
        commands.put(key, value);
    }

    public void replaceCommand(int key, String value)
    {
        addCommand(key, value);
    }

    public int size()
    {
        return commands.size();
    }

    public String toString()
    {
        Set set = commands.entrySet();
        Iterator iterate = set.iterator();
        String output = "List of commands available:\n";
        while(iterate.hasNext())
        {
            Map.Entry i = (Map.Entry) iterate.next();
            output += i.getKey() + " - " + i.getValue() + "\n";
        }

        return output;
    }

    public Map<Integer, String> getCommands(){
        return commands;
    }
}
