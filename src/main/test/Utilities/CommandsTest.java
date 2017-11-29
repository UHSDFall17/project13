package Utilities;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CommandsTest {
    private Commands commands;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testGetSizeOfCommandsOffered_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertEquals(3, commands.size());
    }

    @Test
    public void testGetSizeOfCommandsOffered_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertNotEquals(4, commands.size());
    }

    @Test
    public void testAddCommand_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        commands.addCommand(testCommandsOffered.length+1,"Command D");
        assertNotEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C\n", commands.toString()); //missing new command
    }

    @Test
    public void testAddCommand_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        commands.addCommand(testCommandsOffered.length+1,"Command D");
        assertEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C\n4 - Command D\n", commands.toString());
    }

    @Test
    public void testCommandsToString_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C\n", commands.toString());
    }

    @Test
    public void testCommandsToString_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertNotEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C", commands.toString()); //missing \n (end line)
    }

    @Test
    public void testCommandsHashMapPut_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);

        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        for(int i=1; i <= testCommandsOffered.length; i++)
        {
            hashMap.put(i, testCommandsOffered[i-1]);
        }

        assertEquals(commands.getCommands(), hashMap);
    }

    @Test
    public void testCommandsHashMapPut_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C", "Command D"};
        commands = new Commands(testCommandsOffered);

        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        for(int i=1; i <= testCommandsOffered.length-1; i++) //hashmap will have 1 less than it is supposed to
        {
            hashMap.put(i, testCommandsOffered[i-1]);
        }

        assertNotEquals(commands.getCommands(), hashMap);
    }

    @Test
    public void testReplaceCommand_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        commands.replaceCommand(1, "Replaced Command");

        assertEquals("List of commands available:\n1 - Replaced Command\n2 - Command B\n3 - Command C\n", commands.toString());
    }

    @Test
    public void testReplaceCommand_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        commands.replaceCommand(1, "Replaced Command");

        assertNotEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C\n", commands.toString()); //fail to replace command
    }
}
